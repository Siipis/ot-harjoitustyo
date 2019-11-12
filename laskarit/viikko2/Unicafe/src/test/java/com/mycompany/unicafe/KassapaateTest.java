package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void kortillaOnRahaa() {
        assertEquals(1000, kortti.saldo());
    }

    @Test
    public void konstruktoriAsettaaKassanOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void konstruktoriAsettaaMyydytLounaatOikein() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiLisaaRahaaKassaan() {
        int takaisin = kassa.syoEdullisesti(240);
        assertEquals(0, takaisin);
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiPalauttaaLiianPienetMaksut() {
        int takaisin = kassa.syoEdullisesti(200);
        assertEquals(200, takaisin);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiPalauttaaYlimaaraisetRahat() {
        int takaisin = kassa.syoEdullisesti(340);
        assertEquals(100, takaisin);
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiOnnistuuKortilla() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void syoEdullisestiVahentaaKortinSaldoa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }

    @Test
    public void syoEdullisestiEiVelotaMaksukyvytontaKorttia() {
        Maksukortti kortti = new Maksukortti(100);

        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void syoEdullisestiKortillaEiMuutaKassanSummaa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiLisaaRahaaKassaan() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiPalauttaaLiianPienetMaksut() {
        int takaisin = kassa.syoMaukkaasti(300);
        assertEquals(300, takaisin);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiPalauttaaYlimaaraisetRahat() {
        int takaisin = kassa.syoMaukkaasti(440);
        assertEquals(40, takaisin);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiOnnistuuKortilla() {
        assertTrue(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void syoMaukkaastiVahentaaKortinSaldoa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }

    @Test
    public void syoMaukkaastiEiVelotaMaksukyvytontaKorttia() {
        Maksukortti kortti = new Maksukortti(100);

        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void syoMaukkaastiKortillaEiMuutaKassanSummaa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaatLounaatLasketaanOikein() {
        // Kortilla
        kassa.syoMaukkaasti(kortti);

        // Käteisellä
        kassa.syoMaukkaasti(400);
        kassa.syoMaukkaasti(400);

        // Varmistetaan, ettei edullisia lasketa
        kassa.syoEdullisesti(240);

        assertEquals(3, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullisetLounaatLasketaanOikein() {
        // Kortilla
        kassa.syoEdullisesti(kortti);

        // Käteisellä
        kassa.syoEdullisesti(240);

        // Varmistetaan, ettei maukkaita lasketa
        kassa.syoMaukkaasti(400);
        kassa.syoMaukkaasti(400);
        kassa.syoMaukkaasti(400);

        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void korttiLataaRahaaOikein() {
        kassa.lataaRahaaKortille(kortti, 240);
        assertEquals(1240, kortti.saldo());
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
