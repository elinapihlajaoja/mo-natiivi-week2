Linkki YouTube-videoon: https://youtu.be/m44U9_gEjys

## Datamalli
"Task" data class:

- id = tehtävän tunniste 
- title = otsikko 
- description = kuvaus
- priority = tärkeys
- dueDate = määräpäivä 
- done/not done = tehtävän tila (valmis/kesken) 

## Funktiot
- addTask = Lisää uuden tehtävän
- toddleDone = Vaihtaa tehtävän tilan (valmis/kesken) 
- filterByDone = Valitsee tehtävistä ne, jotka ovat valmiita
- sortByDueDate = Järjestää tehtävät päivämäärän mukaan

## Compose-tilanhallinta:
Jetpack Composessa UI reagoi tilaan. Kun tila muuttuu, Compose piirtää vain tarvittavat osat uudelleen automaattisesti.

## Miksi ViewModel on parempi kuin remember:
**remember** säilyttää tilan vain niin kauan kuin näkymä on muistissa, ja se katoaa esimerkiksi näytön käännössä.
**ViewModel** säilyttää tilan pidempään, selviää konfiguraatiomuutoksista ja pitää sovelluksen logiikan erillään käyttöliittymästä.
