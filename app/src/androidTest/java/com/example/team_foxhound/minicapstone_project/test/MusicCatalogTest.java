package com.example.team_foxhound.minicapstone_project.test;

import com.example.team_foxhound.minicapstone_project.Interfaces.MusicCatalogInterface;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by Nitesh on 01-Nov-15.
 */
public class MusicCatalogTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    @Test
    public void testMusicCatalog() {
        MusicCatalogInterface i = new MusicCatalog();
        assertEquals(i.getPath(), "path");
    }

    @Test
    public void testgetBbm() {
        MusicCatalogInterface i = new MusicCatalog();
    }
    }
    public int getBbm() {

    }

    @Override
    public String getName() {
        return null
}