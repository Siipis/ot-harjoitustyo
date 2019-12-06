# Nimigeneraattori
Sovelluksen avulla käyttäjä voi generoida fiktiivisiä nimiä valitsemistaan aakkosista ja tallentamaan eri aakkospainotukset uudelleenkäytettäviksi "kieliksi".

### Sovelluksen käynnistäminen
Sovelluksen voi käynnistää komennolla `mvn compile exec:java -q`

#### Muut komennot
* Testien suorittaminen: `nvm test`
* Testikattavuusraportti [1]: `mvn jacoco:report`
* Suoritettavan jarin generointi [2]: `mvn package`
* JavaDocin generointi: [3] `mvn javadoc:javadoc`
* Checkstyle-tarkastus [4]: `mvn jxr:jr checkstyle:checkstyle`

[1] Raporttia voi tarkastella avaamalla tiedosto `target/site/jacoco/index.html`

[2] Jar generoituu tiedostoon `target/Namegenerator-1.0.jar`

[3] JavaDoc generoituu tiedostoon `target/site/apidocs/index.html`

[4] Checkstyle-raportti löytyy tiedostosta `target/site/checkstyle.html`

# Dokumentaatio
[Käyttöohje](Nimigeneraattori/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](Nimigeneraattori/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](Nimigeneraattori/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](Nimigeneraattori/dokumentaatio/testaus.md)

[Työaikakirjanpito](Nimigeneraattori/dokumentaatio/tuntikirjanpito.md)

# Releaset
[Viikko 5](https://github.com/Siipis/ot-harjoitustyo/releases/tag/viikko5)

# Kurssimateriaali
Tämä projekti on osa Helsingin yliopiston kurssia Ohjelmistotekniikka.

Kurssimateriaali löytyy täältä: https://github.com/mluukkai/ohjelmistotekniikka-syksy-2019

