package com.example.team_foxhound.minicapstone_project.Interfaces;

import com.example.team_foxhound.minicapstone_project.UserManagement.UserPreferences;

/**
 * Created by Nitesh on 01-Nov-15.
 */
public interface AlgorithmGeneratorInterface {

    public int CalculateHBmax(Object age, String workoutType);

    public int CalculateTHBmax(int HBmax, UserPreferences preferences);

    public void TriggerVib(int HBcurr, int targetHB);

    public int ComputeCal(Object ageLint, int weight, int height, int distance);

}
