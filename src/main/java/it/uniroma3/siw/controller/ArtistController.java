package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.utils.FileUploadUtil;

@Controller
public class ArtistController {
	@Autowired
	private ArtistService artistService;
	

	@GetMapping(value="/admin/formNewArtist")
	public String formNewArtist(Model model) {
		model.addAttribute("artist", new Artist());
		return "admin/formNewArtist.html";
	}

	@GetMapping(value="/admin/indexArtist")
	public String indexArtist() {
		return "admin/indexArtist.html";
	}

	@PostMapping("/admin/artist")
	public String newArtist(@ModelAttribute("artist") Artist artist, Model model,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		if (!artistService.existsByNameAndSurname(artist.getName(), artist.getSurname())) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			artist.setPhoto(fileName);
			this.artistService.saveArtist(artist);
			String uploadDir = "artist-photos/" + artist.getId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			model.addAttribute("artist", artist);
			return "artist.html";
		} else {
			model.addAttribute("messaggioErrore", "Questo artista esiste già");
			return "admin/formNewArtist.html";
		}
	}

	@GetMapping("/artist/{id}")
	public String getArtist(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artist", this.artistService.findArtistById(id));
		return "artist.html";
	}

	@GetMapping("/artist")
	public String getArtists(Model model) {
		model.addAttribute("artists", this.artistService.findAllArtist());
		return "artists.html";
	}

	@GetMapping("/admin/manageArtist")
	public String manageArtists(Model model) {
		model.addAttribute("artists", this.artistService.findAllArtist());
		return "admin/manageArtists.html";
	}

	@GetMapping("/admin/deleteArtist/{id}")
	public String deleteArtist(@PathVariable("id") Long id, Model model) {
		this.artistService.deleteArtist(id);
		model.addAttribute("artists", this.artistService.findAllArtist());
		return "admin/manageArtists.html";
	}

	@GetMapping("/admin/formUpdateArtist/{id}")
	public String formUpdateArtist(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artist", this.artistService.findArtistById(id));
		return "admin/formUpdateArtist.html";
	}

	@PostMapping("/admin/updateArtist/{id}")
	public String updateArtist(@ModelAttribute("artist") Artist newArtist, @PathVariable("id") Long id, Model model) {
		Artist oldArtist = this.artistService.findArtistById(id);
		Artist artist = this.artistService.updateArtist(oldArtist, newArtist);
		this.artistService.saveArtist(artist);
		model.addAttribute("artist", artist );
		return "admin/formUpdateArtist.html";
	}

}
