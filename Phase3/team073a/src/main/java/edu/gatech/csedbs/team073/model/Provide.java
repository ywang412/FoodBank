package edu.gatech.csedbs.team073.model;

/**
 * Created by Phil on 4/12/2017.
 */
public class Provide {


    private int site_id;
    private int food_bank_id;
    private int food_pantry_id;
    private int soup_kitchen_id;
    private int shelter_id;

    public Provide() {
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public int getFood_bank_id() {
        return food_bank_id;
    }

    public void setFood_bank_id(int food_bank_id) {
        this.food_bank_id = food_bank_id;
    }

    public int getFood_pantry_id() {
        return food_pantry_id;
    }

    public void setFood_pantry_id(int food_pantry_id) {
        this.food_pantry_id = food_pantry_id;
    }

    public int getSoup_kitchen_id() {
        return soup_kitchen_id;
    }

    public void setSoup_kitchen_id(int soup_kitchen_id) {
        this.soup_kitchen_id = soup_kitchen_id;
    }

    public int getShelter_id() {
        return shelter_id;
    }

    public void setShelter_id(int shelter_id) {
        this.shelter_id = shelter_id;
    }
}
