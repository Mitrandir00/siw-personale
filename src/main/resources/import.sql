-- 2) Utenti (id deve coincidere con l'ospite)
-- 2) Utenti (id deve coincidere con l'ospite, email è unica e non può essere null)
insert into utente (id, nome, email, password, ruolo) values (2, 'il primo', 'mario.rossi@email.com', '$2a$10$H.RgCzCb.XYajSfI2jcsKehmqQFRX/bBoOg5SDvTQqqX4kmW2o8le', 'UTENTE'); --password=1234
insert into utente (id, nome, email, password, ruolo) values (3, 'il secondo', 'andrea.mari@email.com', '$2a$10$H.RgCzCb.XYajSfI2jcsKehmqQFRX/bBoOg5SDvTQqqX4kmW2o8le', 'STAFF');
insert into utente (id, nome, email, password, ruolo) values (4, 'Luca Verdi', 'luca.verdi@email.com','$2a$10$H.RgCzCb.XYajSfI2jcsKehmqQFRX/bBoOg5SDvTQqqX4kmW2o8le', 'UTENTE'); -- password=1234
insert into utente (id, nome, email, password, ruolo) values (5, 'Giulia Bianchi', 'giulia.bianchi@email.com', '$2a$10$H.RgCzCb.XYajSfI2jcsKehmqQFRX/bBoOg5SDvTQqqX4kmW2o8le', 'UTENTE'); -- password=1234


-- 3) Autori
insert into autore (id, nome, cognome, data_nascita, data_morte, nazionalita, url_image, biografia) values (1, 'Italo', 'Calvino', '1923-10-15', '1985-09-19', 'Italiana', 'images/profiloCalvino.jpg', 'Scrittore italiano noto per le sue opere di narrativa fantastica e sperimentale, come "Le città invisibili". La sua scrittura combina razionalità e immaginazione, e ha lasciato un''impronta indelebile nella letteratura del Novecento.');
insert into autore (id, nome, cognome, data_nascita, data_morte, nazionalita, url_image, biografia) values (2, 'Umberto', 'Eco', '1932-01-05', '2016-02-19', 'Italiana', 'images/ProfiloEco.jpg', 'Semiologo e scrittore, autore di "Il nome della rosa", celebre per i suoi saggi e romanzi. La sua opera unisce rigore accademico e narrativa avvincente, esplorando temi storici, filosofici e semiotici con grande profondità e ironia.');
insert into autore (id, nome, cognome, data_nascita, data_morte, nazionalita, url_image, biografia) values (3, 'J.K.', 'Rowling', '1965-07-31', NULL, 'Britannica', 'images/ProfiloRowling.jpg', 'Celebre per la serie di libri di Harry Potter, fenomeno globale della letteratura per ragazzi. La sua saga ha influenzato un''intera generazione, combinando magia, crescita personale e tematiche sociali in un universo dettagliato e amato in tutto il mondo.');
insert into autore (id, nome, cognome, data_nascita, data_morte, nazionalita, url_image, biografia) values (4, 'Fëdor', 'Dostoevskij', '1821-11-11', '1881-02-09', 'Russa', 'images/ProfiloDostevskij.jpg', 'Scrittore russo celebre per i suoi romanzi psicologici e filosofici come "Delitto e castigo". Le sue opere esplorano le profondità della coscienza umana, la fede, il libero arbitrio e la colpa, influenzando profondamente la letteratura mondiale.');
insert into autore (id, nome, cognome, data_nascita, data_morte, nazionalita, url_image, biografia) values (5, 'Brandon', 'Sanderson', '1975-12-19', NULL, 'Statunitense', 'images/ProfiloSanderson.jpg', 'Autore statunitense di fantasy noto per la sua intricata costruzione di mondi e serie come "Mistborn". È celebre per i suoi sistemi magici coerenti, il ritmo serrato e la capacità di intrecciare storie complesse mantenendo coerenza e originalità.');
insert into autore (id, nome, cognome, data_nascita, data_morte, nazionalita, url_image, biografia) values (6, 'José', 'Saramago', '1922-11-16', '2010-06-18', 'Portoghese', 'images/profiloSaramago.webp', 'Scrittore portoghese premio Nobel, autore di opere simboliche e allegoriche come "Cecità". La sua scrittura si distingue per uno stile unico e provocatorio, affrontando tematiche sociali e morali con una profonda sensibilità umanistica.');
insert into autore (id, nome, cognome, data_nascita, data_morte, nazionalita, url_image, biografia) values (7, 'Margaret', 'Atwood', '1939-11-18', NULL, 'Canadese', 'images/profiloAtwood.jpg', 'Scrittrice canadese, autrice di romanzi distopici e femministi come "Il racconto dell''ancella". Le sue opere analizzano con lucidità i rapporti di potere, la condizione femminile e i rischi della società contemporanea con una voce critica e visionaria.');
insert into autore (id, nome, cognome, data_nascita, data_morte, nazionalita, url_image, biografia) values (8, 'Kazuo', 'Ishiguro', '1954-11-08', NULL, 'Britannica', 'images/profiloIshiguro.webp', 'Scrittore britannico di origine giapponese, vincitore del Nobel per la letteratura nel 2017. Le sue opere, come "Quel che resta del giorno", trattano con eleganza temi come la memoria, l''identità e il rimpianto, attraverso uno stile sobrio e malinconico.');
insert into autore (id, nome, cognome, data_nascita, data_morte, nazionalita, url_image, biografia) values (9, 'Haruki', 'Murakami', '1949-01-12', NULL, 'Giapponese', 'images/profiloMurakami.webp', 'Autore giapponese noto per i suoi romanzi surreali e introspettivi come "Norwegian Wood". Le sue storie fondono realismo magico, musica, solitudine e ricerca del sé in un''atmosfera sospesa, affascinante e profondamente emotiva.');

-- 4) Libri (senza usare sequence se non generata)
insert into libro (id, titolo, data, trama) values (1, 'Il barone rampante', '1957-01-01', 'Cosimo Piovasco di Rondò, giovane rampollo di una famiglia nobile del Settecento, decide un giorno di salire su un albero e non scenderne mai più. Attraverso questo gesto radicale, osserva la società dall''alto, partecipa agli eventi storici del suo tempo, e costruisce un''esistenza autonoma e filosofica, senza mai abbandonare i rami.');
insert into libro (id, titolo, data, trama) values (2, 'Il nome della rosa', '1980-01-01', 'Nel 1327, in un remoto monastero benedettino del Nord Italia, il frate Guglielmo da Baskerville indaga su una serie di omicidi misteriosi. Tra biblioteche segrete, dispute teologiche e atmosfere gotiche, si dipana un intrigo che mette in discussione la fede, il sapere e il potere ecclesiastico.');
insert into libro (id, titolo, data, trama) values (3, 'Harry Potter e la pietra filosofale', '1997-06-26', 'Harry scopre di essere un mago nel giorno del suo undicesimo compleanno e viene accolto alla scuola di magia di Hogwarts. Lì fa amicizie, scopre un mondo incantato e affronta il primo scontro con l''oscuro signore Voldemort, che cerca di tornare al potere grazie alla leggendaria Pietra Filosofale.');
insert into libro (id, titolo, data, trama) values (4, 'Marcovaldo', '1963-01-01', 'Marcovaldo è un operaio che vive in una grande città industriale. Sensibile alla natura e ai suoi cambiamenti stagionali, si scontra con una realtà urbana ostile e alienante. Le sue vicende, ironiche e malinconiche, raccontano la tensione tra sogno e modernità, tra desiderio e bisogno quotidiano.');
insert into libro (id, titolo, data, trama) values (5, 'L''Idiota', '1869-01-01', 'Il principe Myškin, considerato un idiota per la sua bontà e semplicità, ritorna in Russia dopo una cura in Svizzera. Immerso in una società corrotta e cinica, diventa il centro di tensioni amorose e morali, fino a un tragico epilogo che mette in luce l''incompatibilità tra purezza e mondo reale.');
insert into libro (id, titolo, data, trama) values (6, 'Le notti bianche', '1848-01-01', 'In una San Pietroburgo immersa in una luce irreale, un giovane sognatore solitario incontra Nasten''ka, una ragazza malinconica. Per quattro notti, i due si confidano sogni, speranze e dolori, in una danza fragile tra amore platonico e disillusione. Il finale è dolceamaro e profondamente umano.');
insert into libro (id, titolo, data, trama) values (7, 'La via dei Re', '2010-08-31', 'Nel continente di Roshar, devastato da tempeste e guerre millenarie, il destino di re, schiavi e cavalieri si intreccia tra profezie, magie dimenticate e antichi poteri che stanno per risvegliarsi. È l''inizio di una saga epica dove ogni personaggio è chiamato a compiere scelte che cambieranno la storia.');
insert into libro (id, titolo, data, trama) values (8, 'Mistborn: L''ultimo impero', '2006-07-17', 'In un mondo oscuro governato dal crudele Lord Reggente, Vin, una giovane ladra, scopre di possedere potenti abilità magiche legate ai metalli. Unendosi a un gruppo di ribelli, intraprende una missione impossibile: rovesciare l''Impero e restituire la speranza a un popolo oppresso da mille anni.');
insert into libro (id, titolo, data, trama) values (9, 'Il vangelo secondo Gesù Cristo', '1991-01-01', 'José Saramago racconta la vita di Gesù con uno sguardo umanissimo, mettendo in discussione la visione tradizionale della religione. Tra dubbi, sogni e conflitti interiori, emerge un Cristo tormentato, intrappolato nel disegno di un Dio ambizioso e controverso. Un romanzo provocatorio e intenso.');
insert into libro (id, titolo, data, trama) values (10, 'Harry Potter e la camera dei segreti', '1998-07-02', 'Durante il secondo anno a Hogwarts, Harry scopre che una misteriosa Camera dei Segreti è stata riaperta, liberando un antico mostro che pietrifica gli studenti. Tra sospetti, fantasmi e vecchi diari, Harry dovrà affrontare nuove prove per salvare la scuola e svelare segreti del passato.');
insert into libro (id, titolo, data, trama) values (11, 'Harry Potter e il prigioniero di Azkaban', '1999-07-08', 'Harry scopre che Sirius Black, un pericoloso detenuto evaso da Azkaban, potrebbe essere legato al suo passato. Mentre nuovi misteri si intrecciano con l''arrivo di creature magiche e insegnanti enigmatici, la verità su tradimenti e legami familiari viene lentamente alla luce.');
insert into libro (id, titolo, data, trama) values (12, 'Harry Potter e il calice di fuoco', '2000-07-08', 'Hogwarts ospita il Torneo Tremaghi, una competizione magica mortale tra scuole. Harry viene misteriosamente selezionato per partecipare, affrontando sfide pericolose e scoprendo che le forze oscure stanno tramando nell''ombra. Il ritorno di Voldemort segna un punto di svolta drammatico.');
insert into libro (id, titolo, data, trama) values (13, 'Harry Potter e l''Ordine della Fenice', '2003-06-21', 'Dopo il ritorno di Voldemort, il Ministero della Magia nega la verità e cerca di screditare Harry. Intanto, si riforma l''Ordine della Fenice e nasce un movimento di resistenza tra gli studenti. Hogwarts diventa un campo di battaglia ideologico e magico.');
insert into libro (id, titolo, data, trama) values (14, 'Harry Potter e il principe mezzosangue', '2005-07-16', 'Harry, aiutato da Silente, scopre nuovi dettagli sull''infanzia di Voldemort e sui suoi Horcrux, oggetti oscuri che lo rendono immortale. Tra drammi adolescenziali e rivelazioni sconcertanti, si avvicina il momento della resa dei conti e una perdita sconvolge il mondo magico.');
insert into libro (id, titolo, data, trama) values (15, 'Harry Potter e i Doni della Morte', '2007-07-21', 'Harry, Ron e Hermione abbandonano Hogwarts per cercare e distruggere gli Horcrux di Voldemort. In un viaggio pieno di pericoli, segreti e sacrifici, il trio affronta l''ultima grande battaglia per la libertà del mondo magico. Il destino di Harry si compie in uno scontro finale epico.');
insert into libro (id, titolo, data, trama) values (16, 'Il visconte dimezzato', '1952-01-01', 'Il visconte Medardo di Terralba viene colpito da una palla di cannone durante una battaglia contro i Turchi e si divide in due metà: una malvagia e una buona. Le due metà vivono separatamente, creando caos e confusione fino a un sorprendente ricongiungimento.');
insert into libro (id, titolo, data, trama) values (17, 'Il cavaliere inesistente', '1959-01-01', 'Agilulfo è un cavaliere che esiste solo grazie alla sua volontà e alla sua armatura vuota. In un mondo medievale popolato da personaggi paradossali, si muove tra guerra, burocrazia e filosofia, ponendo domande profonde sull''identità e il senso dell''esistenza.');
insert into libro (id, titolo, data, trama) values (18, 'Le città invisibili', '1972-01-01', 'Marco Polo descrive a Kublai Khan una serie di città fantastiche, ognuna delle quali rappresenta un concetto, un sogno o una metafora. Il libro è un viaggio immaginifico e riflessivo, un catalogo poetico dell''anima umana e delle sue infinite possibilità.');
insert into libro (id, titolo, data, trama) values (19, 'Il pendolo di Foucault', '1988-01-01', 'Tre redattori editoriali giocano a inventare un finto complotto esoterico legato ai Templari. Ben presto, la finzione prende vita e li trascina in una spirale di paranoia e mistero. Umberto Eco esplora la sete di significato, l''ossessione per il segreto e il potere delle narrazioni.');
insert into libro (id, titolo, data, trama) values (20, 'Baudolino', '2000-01-01', 'Baudolino, un ragazzo dotato di una fervida immaginazione, vive straordinarie avventure nel Medioevo, tra imperatori, crociati e regni leggendari. La sua storia, narrata in prima persona, mescola menzogna e verità, raccontando il potere del mito e della narrazione nella costruzione del mondo.');
insert into libro (id, titolo, data, trama) values (21, 'Il cimitero di Praga', '2010-01-01', 'Simonini, un falsificatore e cospiratore del XIX secolo, si muove tra spie, sette segrete e complotti, contribuendo a creare documenti che influenzeranno la storia. Un thriller storico dove la falsità diventa verità e il male si nasconde dietro ideologie e religione.');
insert into libro (id, titolo, data, trama) values (22, 'Delitto e castigo', '1866-01-01', 'Raskolnikov, giovane studente di San Pietroburgo, uccide una vecchia usuraia convinto di poter giustificare l''omicidio con ideali superiori. Ma il peso della colpa e l''inesorabile indagine dell''ispettore Porfirij lo conducono a una profonda crisi morale.');
insert into libro (id, titolo, data, trama) values (23, 'I fratelli Karamazov', '1880-01-01', 'Tre fratelli, ognuno rappresentante di una visione del mondo  (la fede, la ragione e il desiderio) si trovano coinvolti nella morte del padre depravato. Un romanzo monumentale che esplora la giustizia, il libero arbitrio e la complessità dell''animo umano.');
insert into libro (id, titolo, data, trama) values (24, 'Ricordi dal sottosuolo', '1864-01-01', 'Un uomo solo e risentito si ritira dal mondo e scrive un lungo monologo sull''inutilità della razionalità e sulla contraddizione dell''essere umano. Con tono acido e provocatorio, Dostoevskij anticipa temi esistenzialisti e mette a nudo l''angoscia moderna.');
insert into libro (id, titolo, data, trama) values (25, 'Mistborn: Il pozzo dell''ascensione', '2007-08-21', 'Dopo la caduta del Lord Reggente, Vin e i suoi alleati devono affrontare la difficile gestione del potere. Il regno è minacciato da forze esterne e nemici interni, mentre misteri antichi legati al Pozzo dell''Ascensione iniziano a emergere, mettendo in discussione ogni certezza.');
insert into libro (id, titolo, data, trama) values (26, 'Mistborn: Il campione delle ere', '2008-07-01', 'Il mondo è sull''orlo della distruzione. Vin e Elend cercano disperatamente indizi per fermare la catastrofe imminente. La verità sulle divinità, sull''ordine cosmico e sul destino degli uomini sarà finalmente rivelata in un finale grandioso e commovente.');
insert into libro (id, titolo, data, trama) values (27, 'Parole di luce', '2014-07-22', 'Secondo libro della serie “Le Cronache della Folgoluce”, continua la storia epica in un mondo dilaniato dalla guerra. Tra profezie, lotte interiori e battaglie titaniche, i personaggi affrontano il peso delle loro scelte e cercano di far emergere la verità in un mare di inganni.');
insert into libro (id, titolo, data, trama) values (28, 'Cecità', '1995-03-01', 'Un''intera città viene colpita da un''epidemia improvvisa di cecità. Il governo isola i malati, lasciandoli al caos, alla violenza e alla perdita di umanità. Solo una donna, ancora vedente, cerca di guidare un gruppo verso la sopravvivenza. Una potente allegoria sulla fragilità civile.');
insert into libro (id, titolo, data, trama) values (29, 'Le intermittenze della morte', '2005-01-01', 'Un giorno, in un paese qualsiasi, la morte smette improvvisamente di colpire. All''inizio è una festa, ma ben presto sorgono problemi etici, politici e religiosi. La morte stessa, personificata, decide di intervenire, portando con sé riflessioni profonde sul senso della vita.');
insert into libro (id, titolo, data, trama) values (30, 'Il racconto dell''isola sconosciuta', '1997-01-01', 'Un uomo chiede al re una barca per cercare un''isola che nessuno conosce. Nonostante l''incredulità generale, parte alla ricerca dell''ignoto, accompagnato da una donna. Un breve ma intenso racconto allegorico sulla libertà, l''immaginazione e il desiderio di esplorare se stessi.');
insert into libro (id, titolo, data, trama) values (31, 'Il racconto dell''ancella', '1985-08-17', 'In una società teocratica e totalitaria chiamata Gilead, le donne sono private di ogni diritto. Le ancelle, come Difred, sono costrette alla riproduzione per conto delle élite. Tra flashback e resistenza silenziosa, si dipana un potente atto di denuncia contro l''oppressione patriarcale.');
insert into libro (id, titolo, data, trama) values (32, 'L''ultimo degli uomini', '2003-03-01', 'Nel futuro, l''umanità è diventata sterile. Il mondo è dominato da disperazione e violenza. Quando nasce l''ultima speranza sotto forma di una donna incinta, l''ex attivista Theo deve proteggerla e guidarla verso la salvezza, in un viaggio simbolico di rinascita e redenzione.');
insert into libro (id, titolo, data, trama) values (33, 'Quel che resta del giorno', '1989-10-01', 'Stevens, un maggiordomo inglese, ripercorre la propria vita durante un viaggio attraverso la campagna. Mentre riflette sulla fedeltà al dovere e sui sentimenti repressi verso la governante Miss Kenton, emergono dubbi sul senso del sacrificio e sulle occasioni perdute.');
insert into libro (id, titolo, data, trama) values (34, 'Non lasciarmi', '2005-03-01', 'In un''Inghilterra distopica, tre amici crescono in un collegio isolato, ignari del loro vero scopo. Quando la verità viene svelata (sono cloni creati per la donazione di organi) iniziano a cercare una via d''uscita, lottando per un amore e una libertà che sembrano impossibili.');
insert into libro (id, titolo, data, trama) values (35, 'Norwegian Wood', '1987-09-04', 'Torū Watanabe, uno studente universitario nella Tokyo degli anni Sessanta, rievoca la sua giovinezza segnata dall''amore per due ragazze molto diverse: Naoko, fragile e malinconica, e Midori, vivace e concreta. Un racconto intimo sulla perdita, la memoria e il dolore dell''adolescenza.');
insert into libro (id, titolo, data, trama) values (36, 'Kafka sulla spiaggia', '2002-09-12', 'Kafka Tamura, un quindicenne in fuga da casa, e Nakata, un anziano che parla con i gatti, intraprendono due viaggi paralleli e surreali che si intrecciano tra sogno e realtà. Murakami crea un labirinto onirico, denso di simboli, mitologia e misteri esistenziali.');




-- 5) URL immagini per i libri
insert into libro_url_Image (libro_id, url_Image) values(1, 'images/copertinaBaroneRampante.jpg');
insert into libro_url_Image (libro_id, url_Image) values(1, 'images/copertinaBaroneRampante2.jpg');
insert into libro_url_Image (libro_id, url_Image) values(2, 'images/copertinaNomeDellaRosa.jpg');
insert into libro_url_Image (libro_id, url_Image) values(3, 'images/copertinaHarryPotter1.jpg');
insert into libro_url_Image (libro_id, url_Image) values(4, 'images/copertinaMarcovaldo.jpg');
insert into libro_url_Image (libro_id, url_Image) values(5, 'images/CopertinaLidiota.jpg');
insert into libro_url_Image (libro_id, url_Image) values(6, 'images/copertinaNottiBianche.jpg');
insert into libro_url_Image (libro_id, url_Image) values(7, 'images/copertinaLaViaDeiRe.jpg');
insert into libro_url_Image (libro_id, url_Image) values(8, 'images/copertinaUltimoImpero.jpg');
insert into libro_url_Image (libro_id, url_Image) values(9, 'images/copertinaVangeloGesu.jpg');
insert into libro_url_Image (libro_id, url_Image) values(10, 'images/copertinaHarryPotter2.jpg');
insert into libro_url_Image (libro_id, url_Image) values(11, 'images/copertinaHarryPotter3.jpg');
insert into libro_url_Image (libro_id, url_Image) values(12, 'images/copertinaHarryPotter4.jpg');
insert into libro_url_Image (libro_id, url_Image) values(13, 'images/copertinaHarryPotter5.jpg');
insert into libro_url_Image (libro_id, url_Image) values(14, 'images/copertinaHarryPotter6.jpg');
insert into libro_url_Image (libro_id, url_Image) values(15, 'images/copertinaHarryPotter7.jpg');
insert into libro_url_Image (libro_id, url_Image) values(16, 'images/copertinaVisconteDimezzato.jpg');
insert into libro_url_Image (libro_id, url_Image) values(17, 'images/copertinaCavaliereInesistente.jpg');
insert into libro_url_Image (libro_id, url_Image) values(18, 'images/copertinaCittaInvisibili.jpg');
insert into libro_url_Image (libro_id, url_Image) values(19, 'images/copertinaPendoloDiFoucault.jpg');
insert into libro_url_Image (libro_id, url_Image) values(20, 'images/copertinaBaudolino.jpg');
insert into libro_url_Image (libro_id, url_Image) values(21, 'images/copertinaCimiteroDiPraga.jpg');
insert into libro_url_Image (libro_id, url_Image) values(22, 'images/copertinaDelittoECastigo.jpg');
insert into libro_url_Image (libro_id, url_Image) values(23, 'images/copertinaFratelliKaramazov.jpg');
insert into libro_url_Image (libro_id, url_Image) values(24, 'images/copertinaRicordiDalSottosuolo.jpg');
insert into libro_url_Image (libro_id, url_Image) values(25, 'images/copertinaPozzoDellAscensione.jpg');
insert into libro_url_Image (libro_id, url_Image) values(26, 'images/copertinaCampioneDelleEre.jpg');
insert into libro_url_Image (libro_id, url_Image) values(27, 'images/copertinaParoleDiLuce.jpg');
insert into libro_url_Image (libro_id, url_Image) values(28, 'images/copertinaCecita.jpg');
insert into libro_url_Image (libro_id, url_Image) values(29, 'images/copertinaIntermittenzeDellaMorte.jpg');
insert into libro_url_Image (libro_id, url_Image) values(30, 'images/copertinaRaccontoDellIsolaSconosciuta.jpg');
insert into libro_url_Image (libro_id, url_Image) values(31, 'images/copertinaRaccontoDellAncella.gif');
insert into libro_url_Image (libro_id, url_Image) values(32, 'images/copertinaUltimoDegliUomini.jpg');
insert into libro_url_Image (libro_id, url_Image) values(33, 'images/copertinaQuelCheRestaDelGiorno.jpg');
insert into libro_url_Image (libro_id, url_Image) values(34, 'images/copertinaNonLasciarmi.jpg');
insert into libro_url_Image (libro_id, url_Image) values(35, 'images/copertinaNorwegianWood.jpg');
insert into libro_url_Image (libro_id, url_Image) values(36, 'images/copertinaKafkaSullaSpiaggia.jpg');


-- 6) Recensioni
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(1, 'Stupendo', 5, 'Un capolavoro della letteratura.', 2, 1);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(2, 'Molto avvincente', 4, 'Mi è piaciuto molto, consigliato.', 2, 2);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(3, 'Potente e struggente', 5, 'Un libro profondo e commovente.', 4, 28);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(4, 'Magico!', 5, 'L''inizio perfetto di una saga indimenticabile.', 5, 3); 
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(5, 'Distopico e attuale', 4, '1984 è un capolavoro sempre più attuale.', 2, 1);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(6, 'Affascinante e profondo', 5, 'Un romanzo che fa riflettere sul progresso e sull''identità.', 3, 2);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(7, 'Ribelle e poetico', 5, 'Cosimo è un personaggio indimenticabile. Calvino ha saputo trasformare una fiaba in una riflessione sulla libertà.', 2, 1);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(8, 'Un classico moderno', 4, 'Letto da ragazzo, riletto da adulto. Sempre emozionante, mai banale.', 2, 1);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(9, 'Atmosfera unica', 5, 'Intrigante e profondo, ti tiene incollato fino all''ultima pagina.', 2, 2);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(10, 'Denso ma affascinante', 4, 'Alcune parti sono complesse, ma l''ambientazione è magistrale.', 2, 2);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(11, 'Dolce e struggente', 5, 'Un racconto breve ma toccante, perfetto per chi ama le emozioni sussurrate.', 2, 6);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(12, 'Romantico e malinconico', 4, 'Mi ha ricordato quanto siano potenti le attese e le illusioni.', 2, 6);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(13, 'Grandioso', 5, 'Il secondo volume non delude. Ancora più tensione e profondità.', 2, 27);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(14, 'Tanti colpi di scena', 4, 'Ogni capitolo è una sorpresa. Sanderson si supera.', 2, 27);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(15, 'Magia pura', 5, 'Ogni volta che lo rileggo riscopro l''incanto dell''infanzia.', 3, 3);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(16, 'Intramontabile', 5, 'Una storia che ha segnato la mia generazione. Perfetto per chi ama l''avventura.', 3, 3);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(17, 'Innovativo', 5, 'Il sistema magico è fantastico, mai visto nulla di simile.', 3, 8);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(18, 'Una sorpresa', 4, 'Non conoscevo l''autore, ma mi ha conquistato. Trama coinvolgente.', 3, 8);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(19, 'Breve ma profondo', 4, 'Una parabola sulla ricerca e sull''immaginazione. Poetico.', 3, 30);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(20, 'Una gemma nascosta', 5, 'Mi ha fatto riflettere molto in poche pagine. Bellissimo.', 3, 30);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(21, 'Intenso e tragico', 5, 'Una storia che ti scava dentro. Il principe è una figura luminosa in un mondo oscuro.', 4, 5);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(22, 'Dostoevskij all''ennesima potenza', 4, 'Mi ha fatto riflettere sulla bontà e sull''ingenuità. Duro ma essenziale.', 4, 5);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(23, 'Epicità allo stato puro', 5, 'Sanderson non delude mai. Un mondo enorme e pieno di dettagli.', 4, 7);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(24, 'Impegnativo ma gratificante', 4, 'Lento all''inizio, poi esplode. Consigliato a chi ama il fantasy complesso.', 4, 7),
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(25, 'Un pugno allo stomaco', 5, 'Cecità è un''esperienza più che una lettura. Crudo e umano.', 4, 28);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(26, 'Straziante e necessario', 5, 'Una metafora potente sulla nostra società. Da leggere assolutamente.', 4, 28);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(27, 'Magia pura', 5, 'Ogni volta che lo rileggo riscopro l''incanto dell''infanzia.', 5, 3);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(28, 'Intramontabile', 5, 'Una storia che ha segnato la mia generazione. Perfetto per chi ama l''avventura.', 5, 3);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(29, 'Più oscuro del primo', 4, 'Harry cresce e lo si sente. Bellissimo il mistero della camera.', 5, 10);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(30, 'Divertente e teso', 5, 'Mi ha tenuta incollata fino alla fine. Bella evoluzione dei personaggi.', 5, 10);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(31, 'Originale e provocatorio', 4, 'Il concetto è geniale. Fa riflettere sul valore della morte.', 5, 29);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(32, 'Saramago colpisce ancora', 5, 'Un romanzo meno cupo di Cecità, ma altrettanto potente.', 5, 29);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(35, 'Emozionante', 3, 'Mi ha sorpreso per la profondità dei temi trattati.', 4, 4);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(36, 'Profondo', 3, 'Una lettura piacevole anche se un po'' lenta in alcuni punti.', 2, 4);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(37, 'Emozionante', 5, 'Una storia toccante che consiglio a tutti.', 2, 7);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(38, 'Deludente', 4, 'Mi ha sorpreso per la profondità dei temi trattati.', 2, 9);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(39, 'Interessante', 3, 'Una lettura piacevole anche se un po'' lenta in alcuni punti.', 2, 9);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(40, 'Deludente', 3, 'Ottimo libro per chi cerca qualcosa di originale.', 5, 11);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(41, 'Profondo', 4, 'Una storia toccante che consiglio a tutti.', 4, 11);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(42, 'Sorprendente', 3, 'Trama ben costruita, personaggi credibili.', 3, 12);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(43, 'Emozionante', 4, 'Ho apprezzato molto lo stile dell''autore.', 4, 12);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(44, 'Profondo', 3, 'Trama ben costruita, personaggi credibili.', 4, 13);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(45, 'Interessante', 3, 'Non mi ha convinto del tutto, ma offre spunti interessanti.', 2, 13);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(46, 'Coinvolgente', 4, 'Una storia toccante che consiglio a tutti.', 4, 14);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(47, 'Sorprendente', 3, 'Ottimo libro per chi cerca qualcosa di originale.', 5, 14);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(48, 'Deludente', 3, 'Non mi ha convinto del tutto, ma offre spunti interessanti.', 2, 14);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(49, 'Deludente', 4, 'Trama ben costruita, personaggi credibili.', 4, 15);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(50, 'Deludente', 3, 'Ottimo libro per chi cerca qualcosa di originale.', 2, 15);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(51, 'Interessante', 5, 'Una lettura piacevole anche se un po'' lenta in alcuni punti.', 4, 16);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(52, 'Interessante', 3, 'Trama ben costruita, personaggi credibili.', 2, 16);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(53, 'Ben scritto', 4, 'Non mi ha convinto del tutto, ma offre spunti interessanti.', 4, 17);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(54, 'Profondo', 4, 'Ho apprezzato molto lo stile dell''autore.', 3, 17);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(55, 'Emozionante', 4, 'Ottimo libro per chi cerca qualcosa di originale.', 2, 18);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(56, 'Deludente', 5, 'Una lettura piacevole anche se un po'' lenta in alcuni punti.', 3, 18);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(57, 'Profondo', 4, 'Non mi ha convinto del tutto, ma offre spunti interessanti.', 4, 19);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(58, 'Emozionante', 5, 'Una storia toccante che consiglio a tutti.', 3, 19);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(59, 'Emozionante', 4, 'Trama ben costruita, personaggi credibili.', 2, 20);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(60, 'Profondo', 3, 'Trama ben costruita, personaggi credibili.', 4, 20);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(61, 'Ben scritto', 4, 'Mi ha sorpreso per la profondità dei temi trattati.', 3, 21);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(62, 'Deludente', 5, 'Ho apprezzato molto lo stile dell''autore.', 3, 21);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(63, 'Emozionante', 4, 'Non mi ha convinto del tutto, ma offre spunti interessanti.', 5, 21);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(64, 'Profondo', 4, 'Una lettura piacevole anche se un po'' lenta in alcuni punti.', 3, 22);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(65, 'Emozionante', 5, 'Una storia toccante che consiglio a tutti.', 4, 22);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(66, 'Emozionante', 5, 'Non mi ha convinto del tutto, ma offre spunti interessanti.', 5, 23);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(67, 'Coinvolgente', 3, 'Una lettura piacevole anche se un po'' lenta in alcuni punti.', 5, 23);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(68, 'Interessante', 3, 'Trama ben costruita, personaggi credibili.', 2, 24);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(69, 'Profondo', 5, 'Una lettura piacevole anche se un po'' lenta in alcuni punti.', 5, 24);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(70, 'Deludente', 3, 'Non mi ha convinto del tutto, ma offre spunti interessanti.', 5, 25);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(71, 'Deludente', 4, 'Una storia toccante che consiglio a tutti.', 4, 25);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(72, 'Deludente', 3, 'Ottimo libro per chi cerca qualcosa di originale.', 2, 26);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(73, 'Emozionante', 5, 'Trama ben costruita, personaggi credibili.', 4, 26);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(74, 'Sorprendente', 5, 'Ho apprezzato molto lo stile dell''autore.', 2, 28);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(75, 'Coinvolgente', 4, 'Una lettura piacevole anche se un po'' lenta in alcuni punti.', 5, 31);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(76, 'Interessante', 5, 'Ottimo libro per chi cerca qualcosa di originale.', 4, 31);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(77, 'Deludente', 3, 'Una storia toccante che consiglio a tutti.', 2, 32);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(78, 'Sorprendente', 5, 'Ho apprezzato molto lo stile dell''autore.', 3, 32);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(79, 'Profondo', 4, 'Trama ben costruita, personaggi credibili.', 3, 33);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(80, 'Deludente', 5, 'Mi ha sorpreso per la profondità dei temi trattati.', 4, 33);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(81, 'Ben scritto', 3, 'Mi ha sorpreso per la profondità dei temi trattati.', 4, 34);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(82, 'Sorprendente', 4, 'Una lettura piacevole anche se un po'' lenta in alcuni punti.', 2, 34);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(83, 'Profondo', 5, 'Mi ha sorpreso per la profondità dei temi trattati.', 2, 35);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(84, 'Emozionante', 4, 'Trama ben costruita, personaggi credibili.', 2, 35);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(85, 'Sorprendente', 5, 'Trama ben costruita, personaggi credibili.', 3, 35);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(86, 'Profondo', 5, 'Non mi ha convinto del tutto, ma offre spunti interessanti.', 3, 36);
insert into recensione (id, titolo, voto, commento, autore_id, libro_id) values(87, 'Coinvolgente', 5, 'Trama ben costruita, personaggi credibili.', 5, 36);



insert into utente_libri_letti (utente_id, libri_letti_id) values (2, 1); -- Mario Rossi ha letto Il barone rampante
insert into utente_libri_letti (utente_id, libri_letti_id) values (2, 2); -- Mario Rossi ha letto Il nome della rosa
insert into utente_libri_letti (utente_id, libri_letti_id) values (3, 3); -- Andrea Mari ha letto Harry Potter
insert into utente_libri_letti (utente_id, libri_letti_id) values (4, 5); -- L'Idiota
insert into utente_libri_letti (utente_id, libri_letti_id) values (4, 7); -- La via dei Re
insert into utente_libri_letti (utente_id, libri_letti_id) values (4, 28); -- Cecità
insert into utente_libri_letti (utente_id, libri_letti_id) values (5, 3); -- Harry Potter 1
insert into utente_libri_letti (utente_id, libri_letti_id) values (5, 10); -- Harry Potter 2
insert into utente_libri_letti (utente_id, libri_letti_id) values (5, 29); -- Le intermittenze della morte
insert into utente_libri_letti (utente_id, libri_letti_id) values (2, 1);  -- 1984
insert into utente_libri_letti (utente_id, libri_letti_id) values (2, 6);  -- Il maestro e Margherita
insert into utente_libri_letti (utente_id, libri_letti_id) values (2, 27); -- La lunga marcia
insert into utente_libri_letti (utente_id, libri_letti_id) values (3, 2);  -- Il mondo nuovo
insert into utente_libri_letti (utente_id, libri_letti_id) values (3, 8);  -- Il Nome del Vento
insert into utente_libri_letti (utente_id, libri_letti_id) values (3, 30); -- La caverna




-- 7) Join libro ↔ autore (ManyToMany)
insert into libro_autori (libri_id, autori_id) values(1, 1);
insert into libro_autori (libri_id, autori_id) values(2, 2);
insert into libro_autori (libri_id, autori_id) values(3, 3);
insert into libro_autori (libri_id, autori_id) values(4, 1);
insert into libro_autori (libri_id, autori_id) values(5, 4);
insert into libro_autori (libri_id, autori_id) values(6, 4);
insert into libro_autori (libri_id, autori_id) values(7, 5);
insert into libro_autori (libri_id, autori_id) values(8, 5);
insert into libro_autori (libri_id, autori_id) values(9, 6);
insert into libro_autori (libri_id, autori_id) values(10, 3);
insert into libro_autori (libri_id, autori_id) values(11, 3);
insert into libro_autori (libri_id, autori_id) values(12, 3);
insert into libro_autori (libri_id, autori_id) values(13, 3);
insert into libro_autori (libri_id, autori_id) values(14, 3);
insert into libro_autori (libri_id, autori_id) values(15, 3);
insert into libro_autori (libri_id, autori_id) values(16, 1);
insert into libro_autori (libri_id, autori_id) values(17, 1);
insert into libro_autori (libri_id, autori_id) values(18, 1);
insert into libro_autori (libri_id, autori_id) values(19, 2);
insert into libro_autori (libri_id, autori_id) values(20, 2);
insert into libro_autori (libri_id, autori_id) values(21, 2);
insert into libro_autori (libri_id, autori_id) values(22, 4);
insert into libro_autori (libri_id, autori_id) values(23, 4);
insert into libro_autori (libri_id, autori_id) values(24, 4);
insert into libro_autori (libri_id, autori_id) values(25, 5);
insert into libro_autori (libri_id, autori_id) values(26, 5);
insert into libro_autori (libri_id, autori_id) values(27, 5);
insert into libro_autori (libri_id, autori_id) values(28, 6);
insert into libro_autori (libri_id, autori_id) values(29, 6);
insert into libro_autori (libri_id, autori_id) values(30, 6);
insert into libro_autori (libri_id, autori_id) values (31, 7);
insert into libro_autori (libri_id, autori_id) values (32, 8);
insert into libro_autori (libri_id, autori_id) values (33, 8);
insert into libro_autori (libri_id, autori_id) values (34, 8);
insert into libro_autori (libri_id, autori_id) values (35, 9);
insert into libro_autori (libri_id, autori_id) values (36, 9);



-- 9) Aggiorna sequenze, se Hibernate le ha create
SELECT setval('libro_seq', (SELECT MAX(id) FROM libro));
SELECT setval('utente_seq', (SELECT MAX(id) FROM utente));
SELECT setval('autore_seq', (SELECT MAX(id) FROM autore));
SELECT setval('recensione_seq', (SELECT MAX(id) FROM recensione));

