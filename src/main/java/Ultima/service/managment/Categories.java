package Ultima.service.managment;

public interface Categories {

    enum Entity{
        PERSON,
        ORGANIZER,
        TOURNAMENT,
    }

    enum Sex{
        MALE,
        FEMALE
    }


    enum SportCategories{
        RUNNING,
        CYCLING,
        SKIING,

    }

    enum AgeCategories{

        TO_TEN_YEARS_OLD,
        TEN_TO_FIFTEEN,
        FIFTEEN_TO_TWENTY,
        TWENTY_TO_THIRTY,
        THIRTY_TO_FORTY,
        FORTY_TO_FIFTY,
        MORE_OF_FIFTY
    }



}
