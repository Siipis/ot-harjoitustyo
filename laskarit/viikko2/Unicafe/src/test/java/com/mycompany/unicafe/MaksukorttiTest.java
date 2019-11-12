package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void rahanLaittaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(50);
        assertEquals("saldo: 0.60", kortti.toString());
    }

    @Test
    public void rahanOttaminenVahentaaSaldoaOikein() {
        kortti.otaRahaa(3);
        assertEquals("saldo: 0.7", kortti.toString());
    }

    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(15);
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void metodiPalauttaaTrueJosRahatRiittavat() {
        assertTrue(kortti.otaRahaa(5));
    }

    @Test
    public void metodiPalauttaaFalseJosRahatEivatRiita() {
        assertFalse(kortti.otaRahaa(15));
    }
}
