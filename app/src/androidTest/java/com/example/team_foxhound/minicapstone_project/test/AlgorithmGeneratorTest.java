package com.example.team_foxhound.minicapstone_project.test;

import com.example.team_foxhound.minicapstone_project.AlgorithmGenerator.AlgorithmGenerator;
import com.example.team_foxhound.minicapstone_project.Interfaces.AlgorithmGeneratorInterface;
import com.example.team_foxhound.minicapstone_project.UserManagement.UserPreferences;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by Nitesh on 01-Nov-15.
 */
public class AlgorithmGeneratorTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }
    @Test
    public void testCalculateHBmax(){
        AlgorithmGeneratorInterface i = new AlgorithmGenerator();
        assertEquals(i.CalculateHBmax(25,"Intermediate"),120);
    }
    @Test
    public void testCalculateTHBmax(){
        AlgorithmGeneratorInterface i = new AlgorithmGenerator();
        UserPreferences userPreferences = new UserPreferences();
        assertEquals(i.CalculateTHBmax(20,userPreferences),130);
    }
    @Test
    public void TriggerVib(){
    }
}