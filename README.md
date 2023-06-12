# Siwmovie


Casi d’uso di Siwmovie

UC1: Inserimento di un nuovo film.
Attore primario: Amministratore

1) L'amministratore inserisce username e password e si autentica nel sistema
2) Il sistema mostra un elenco di operazioni possibili per l'amministratore
3) L'amministratore sceglie "Operazioni sui film"
4) Il sistema mostra un elenco di possibili operazioni sui film per l'amministratore
5) L'amministratore sceglie l'attività "Inserisci i dati di un film"
6) L'amministratore inserisce il titolo, l'anno di uscita e un'immagine del film
7) L'amministratore conferma l'inserimento di un nuovo film
8) Il sistema mostra un riepilogo dei dati del film appena inserito

UC2: Inserimento di un nuovo artista. 
Attore primario: Amministratore
1) L’amministratore si autentica nel Sistema inserendo username e password
2) Il sistema mostra un elenco di operazioni possibili per l’amministratore
3) L’amministratore sceglie “Operazioni sugli artisti”
4) Il sistema mostra un elenco di possibili operazioni sugli artisti per l’amministratore
5) L’amministratore sceglie l’attività “Inserisci i dati di un artista”
6) L’amministratore inserisce il nome, il cognome, la data di nascita, l’eventuale data di morte e un’immagine
7) L’amministratore conferma l’inserimento di un nuovo artista
8) Il sistema mostra un riepilogo dei dati dell’artista appena inserito

UC3: Inserimento di una nuova recensione. 
Attore primario: un utente del Sistema
1) L'utente si autentica nel sistema inserendo username e password.
2) Il sistema mostra un elenco di operazioni possibili per l’utente
3) L'utente sceglie "Operazioni sui film".
4) Il sistema mostra l’elenco dei film presenti.
5) L'utente sceglie l'attività "Inserisci recensione".
6) L'utente inserisce il titolo, la valutazione e un testo.
7) L'utente conferma l'inserimento di una nuova recensione.
8) Il sistema salva la recensione.

UC4: Modifica di un film. 
Attore primario: Amministratore
1) L’amministratore si autentica nel Sistema inserendo username e password
2) Il sistema mostra un elenco di operazioni possibili per l’amministratore
3) L’amministratore sceglie “Operazioni sui film”
4) Il sistema mostra un elenco di possibili operazioni sui film per l’amministratore
5) L’amministratore sceglie l’attività “Gestisci film”
6) Il sistema mostra l’elenco dei film disponibili
7) L’amministratore sceglie l’attività “Aggiorna” o “Elimina” in riferimento ad uno dei film disponibili
8) Il sistema mostra un riepilogo degli attuali dati del film
9) L’amministratore tramite appositi form inserisce nuovi dati per il regista, gli attori che partecipano al film, e l’immagine del film.
10) L’amministratore conferma le scelte effettuate.
11) Il sistema salva il film modificato


UC5: Modifica di un artista. 
Attore primario: Amministratore
1) L’amministratore si autentica nel Sistema inserendo username e password
2) Il sistema mostra un elenco di operazioni possibili per l’amministratore
3) L’amministratore sceglie “Operazioni sugli artisti”
4) Il sistema mostra un elenco di possibili operazioni sugli artisti per l’amministratore
5) L’amministratore sceglie l’attività “Modifica o elimina artisti”
6) Il sistema mostra l’elenco degli artisti disponibili
7) L’amministratore sceglie l’attività “Aggiorna” in riferimento ad uno degli artisti disponibili
8) Il sistema mostra un riepilogo degli attuali dati dell’artista 
9) L’amministratore tramite appositi form inserisce nuovi dati per nome, cognome, data di nascita ed eventuale data di morte ed inoltre aggiunge una immagine
10) L’amministratore conferma le scelte effettuate
11) Il sistema salva l’artista modificato

UC6: Ricerca di un film per anno. 
Attore primario: Amministratore
1) L’amministratore si autentica nel Sistema inserendo username e password
2 Il sistema mostra un elenco di operazioni possibili per l’amministratore
3) L’amministratore sceglie “Operazioni sui film”
4) Il sistema mostra un elenco di possibili operazioni sui film per l’amministratore
5) L’amministratore sceglie l’attività “Cerca film per anno”
6) Il sistema mostra un form per inserire l’anno 
7) L’amministratore inserisce l’anno e avvia la ricerca.
8) Il sistema mostra i film che soddisfano i requisiti

UC7: Eliminazione di una recensione. 
Attore primario: Amministratore
1) L’amministratore si autentica nel Sistema inserendo username e password
2) Il sistema mostra un elenco di operazioni possibili per l’amministratore
3) L’amministratore sceglie “Operazioni sui film”
4) Il sistema mostra un elenco di possibili operazioni sui film per l’amministratore
5) L’amministratore sceglie l’attività “Elimina recensioni”
6) Il sistema mostra l’elenco dei film presenti nel sistema 
7) L’amministratore sceglie il film di cui vuole cancellare le recensioni
8) Il sistema mostra tutte le recensioni per quel film
9) L’amministratore seleziona la recensione da cancellare, e conferma la sua scelta.
10) Il sistema cancella la recensione.

UC8: Visualizzazione film. 
Attore primario: un utente del Sistema
1)	Il sistema mostra un elenco di operazioni possibili
2)	L’utente sceglie “Elenco film”
3)	Il sistema mostra un elenco dei film presenti
4)	L’utente seleziona uno dei film mostrati
5)	Il sistema mostra i dettagli relativi al film selezionato

UC9: Visualizzazione artisti. 
Attore primario: un utente del Sistema
1)	Il sistema mostra un elenco di operazioni possibili
2)	L’utente sceglie “Elenco artisti”
3)	Il sistema mostra un elenco degli artisti presenti
4)	L’utente seleziona uno degli artisti mostrati
5)	Il sistema mostra i dettagli relativi all’artista selezionato

UC10: Visualizzazione recensioni. 
Attore primario: un utente del Sistema
1) Il sistema mostra un elenco di operazioni possibili
2) L’utente sceglie “Elenco recensioni”
3) Il sistema mostra un elenco dei film presenti
4) L’utente seleziona uno dei film presenti
5) Il sistema mostra tutte le recensioni del film selezionato
