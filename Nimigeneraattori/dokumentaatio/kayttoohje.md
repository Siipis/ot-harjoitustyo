# Käyttöohje
Lataa sovelluksen viimeisin release [täältä](https://github.com/Siipis/ot-harjoitustyo/releases).

### Riippuvuudet
Sovellus olettaa, että SQLite on käytettävissä. Jos SQLite puuttuu, sen voi asentaa [täältä](https://www.sqlite.org/).

## Generaattorin käyttäminen
Sovellus käynnistyy seuraavaan näkymään:

![sovelluksen perusnäkymä](kuvat/sovellus.png)

Ylimpänä on nappi, jonka avulla uuden nimen voi generoida. Alla on kielen konfiguraattori, joka muodostuu 
kirjainpainotusten säädöistä, nimen minimi- ja maksimipituuden asetuksista sekä kirjainten ryhmittymisasetuksista.

### Nimen generoiminen
![nimen generoiminen](kuvat/sovellus_generoitu_nimi.png)

Generate-nappia painamalla sovellukseen tulostuu kieliasetusten mukainen nimi. Nappia uudestaan painamalla nimi 
korvautuu uudella. Kirjainten esiintymistodennäköisyyttä voi säätää ylöspäin vetämällä kirjainkohtaista säädintä oikealle, 
tai halutessaan poistaa kirjaimen käytöstä kokonaan vetämällä säätimisen kokonaan vasempaan.

HUOM! Nykyisessä sovelluksen versiossa Y katsotaan joko vokaaliksi tai konsonantiksi. Näin, koska sovelluksen oletuskieli on englanti.

### Nimen tallentaminen ja lataaminen
![valikko](kuvat/sovellus_menu.png)

Ylänurkan Menu:ta klikkaamalla saa auki valikon, jolla voi luoda uuden nimen (jolloin asetukset palautuvat oletusarvoihin)
tai hallita tallennettuja nimiä. 

Tallentamisen yhteydessä (Menu -> Save) sovellus kysyy nimeä, joka voi joko olla kokonaan uusi nimi tai jo tallennettu nimi. 
Jos samalla nimellä on jo olemassa tallennus, vanha versio korvautuu uudella. Kun tallennus on tehty, kielen voi 
ladata tai poistaa valitsemalla sen listalta klikattuaan Open tai Delete.

### Virheen sattuessa
Jos kieliasetukset ovat virheellisiä, tulostuu virheilmoitus eikä nimeä voi generoida ennen virheen korjaamista.
Virheellistä kieltä ei voi myöskään tallentaa.