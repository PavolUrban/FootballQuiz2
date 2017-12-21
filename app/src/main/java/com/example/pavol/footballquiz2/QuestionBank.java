package com.example.pavol.footballquiz2;

/**
 * Created by pavol on 19. 12. 2017.
 */

public class QuestionBank {

    // array of questions
    private String textQuestions [] = {
            "1. Kto bol víťazom MS2010 v Juhoafrickej republike?",
            "2. Kto z týchto tímov sa nikdy nepredstavil na MS vo futbale?",
            "3. Kto z týchto tímov má najviac víťazstiev v Lige majstrov?",
            "4. Kto je najlepším strelcom histórie MS (do roku 2014)?",
            "5. Ktorý z týchto tímov nebol nikdy na Majstrovstvách sveta?",
            "6. Kto vyhral Majstrovstvá sveta 1930 v Uruguaji?",
            "7. Kto vyhral Majstrovstvá sveta 1934 v Taliansku?",
            "8. Kto vyhral Majstrovstvá sveta 1938 vo Francúzsku?",
            "9. Kto vyhral Majstrovstvá sveta 1950 v Brazílii?",
            "10. Kto vyhral Majstrovstvá sveta 1954 v Švajčiarsku?",
            "11. Kto vyhral Majstrovstvá sveta 1958 vo Švédsku?",
            "12. Kto vyhral Majstrovstvá sveta 1962 v Chile?",
            "13. Kto vyhral Majstrovstvá sveta 1966 v Anglicku?",
            "14. Kto vyhral Majstrovstvá sveta 1970 v Mexiku?",
            "15. Kto vyhral Majstrovstvá sveta 1974 v Západnom Nemecku?",
            "16. Kto vyhral Majstrovstvá sveta 1978 v Argentíne?",
            "17. Kto vyhral Majstrovstvá sveta 1982 v Španielsku?",
            "18. Kto vyhral Majstrovstvá sveta 1986 v Mexiku?",
            "19. Kto vyhral Majstrovstvá sveta 1990 v Taliansku?",
            "20. Kto vyhral Majstrovstvá sveta 1994 v USA?",
            "21. Kto vyhral Majstrovstvá sveta 1998 vo Francúzsku?",
            "22. Kto vyhral Majstrovstvá sveta 2002 v Japonsku a Južnej Kórei?",
            "23. Kto vyhral Majstrovstvá sveta 2006 v Nemecku?",
            "24. Kto vyhral Majstrovstvá sveta 2014 v Brazílii?",
            "25. Aký názov má tento štadión?",
            "26. Aký názov má tento štadión?",
            "27. Aký názov má tento štadión?",
            "28. Kto je na obrázku?",
            "29. Kto je na obrázku?"

    };

    // array of multiple choices for each question




    private String multipleChoice [][] = {
            {"Španielsko", "Holandsko", "Brazília", "Nemecko"},
            {"Trinidad a Tobago", "Kuba", "Salvádor", "Guatemala"},
            {"Nottingham Forest", "Chelsea FC", "Arsenal FC", "CZ Belehrad"},
            {"Miroslav Klose", "Ronaldo", "Gerd Muller", "Pelé"},
            {"Fínsko", "Izrael", "Egypt", "Honduras"},
            {"Uruguaj", "Argentína", "Brazília", "Nemecko"},
            {"Brazília", "Juhoslávia", "Československo", "Taliansko"},
            {"Francúzsko", "Taliansko", "Brazília", "Maďarsko"},
            {"Brazília", "Uruguaj", "Argentína", "Taliansko"},
            {"Západné Nemecko", "Maďarsko", "Uruguaj", "Taliansko"},
            {"Švédsko", "Francúzsko", "Nemecko", "Brazília"},
            {"Československo", "Čile", "Brazília", "Nemecko"},
            {"Portugalsko", "Anglicko", "Nemecko", "ZSSR"},
            {"Brazília", "Taliansko", "Nemecko", "Mexiko"},
            {"Západné Nemecko", "Holandsko", "Poľsko", "Brazília"},
            {"Brazília", "Argentína", "Holandsko", "Francúzsko"},
            {"Španielsko", "Argentína", "Nemecko", "Taliansko"},
            {"Mexiko", "Argentína", "Brazília", "Nemecko"},
            {"Argentína", "Taliansko", "Nemecko", "Anglicko"},
            {"USA", "Brazília", "Taliansko", "Nemecko"},
            {"Francúzsko", "Taliansko", "Brazília", "Holandsko"},
            {"Južná Kórea", "Brazília", "Nemecko", "Turecko"},
            {"Nemecko", "Portugalsko", "Argentína", "Taliansko"},
            {"Brazília", "Holandsko", "Nemecko", "Argentína"},
            {"Bazaly", "Letná", "Pod Dubňom", "Dolíček"},
            {"Old Trafford", "Anfield Road", "Stamford Bridge", "Wembley"},
            {"San Siro", "Delle Alpi", "Signal Iduna Park", "Santiago Bernabeu"},
            {"Diego Maradona","Pelé","Gary Lineker","Eric Cantona"},
            {"Ronaldinho","Rivaldo","Ronaldo","Cristiano Ronaldo"}

            //{"", "", "", ""}

    };

    // array of correct answers - in the same order as array of questions
    private String mCorrectAnswers[] = {"Španielsko", "Guatemala", "Nottingham Forest", "Miroslav Klose","Fínsko","Uruguaj","Taliansko","Taliansko","Uruguaj",
            "Západné Nemecko","Brazília","Brazília", "Anglicko","Brazília","Západné Nemecko","Argentína","Taliansko", "Argentína","Nemecko","Brazília","Francúzsko",
            "Brazília","Taliansko","Nemecko","Bazaly","Wembley","San Siro","Diego Maradona","Ronaldo"};

    // method returns number of questions
    public int getLength(){
        return textQuestions.length;
    }

    // method returns question from array textQuestions[] based on array index
    public String getQuestion(int a) {
        String question = textQuestions[a];
        return question;
    }

    // method return a single multiple choice item for question based on array index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4 as an argument
    public String getChoice(int index, int num) {
        String choice0 = multipleChoice[index][num-1];
        return choice0;
    }

    //  method returns correct answer for the question based on array index
    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }
}
