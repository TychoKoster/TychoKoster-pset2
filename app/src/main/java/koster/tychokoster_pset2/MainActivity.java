package koster.tychokoster_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* Initializes the story class and chooses a story randomly. */
    public Story createStory() throws IOException {
        Random rand = new Random();
        int random_number = rand.nextInt(5) + 1;
        String file = "";
        switch(random_number) {
            case 1:
                file = "madlib0_simple.txt";
                break;
            case 2:
                file = "madlib1_tarzan.txt";
                break;
            case 3:
                file = "madlib2_university.txt";
                break;
            case 4:
                file = "madlib3_clothes.txt";
                break;
            case 5:
                file = "madlib4_dance.txt";
                break;
        }
        InputStream stream = getAssets().open(file);
        Story story = new Story(stream);
        return story;
    }

    /* Starts the new activity when the user hits the "Get started!" button. */
    public void GoToSecond (View view) throws IOException {
        Story story = createStory();
        Intent intent = new Intent (this, SecondActivity.class);
        intent.putExtra("story", story);
        startActivity(intent);
        }
    }
