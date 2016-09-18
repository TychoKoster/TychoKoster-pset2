package koster.tychokoster_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {

    /* Retrieves the intent from the previous activity and calls the createText method. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Intent intentThatStarted = getIntent();
        Story story = (Story) intentThatStarted.getSerializableExtra("story");
        createText(story);
    }

    /* Checks if the user has put in some text.
    *  Takes the user input and puts it into the story that is opened.
    *  Empties the text box so that the use can fill in the next word.
    *  If all placeholders are filled in, it calls the GoToThird method. */
    public void fillInStory(View v) {
        Intent intentThatStarted = getIntent();
        Story story = (Story) intentThatStarted.getSerializableExtra("story");
        EditText given_word = (EditText) findViewById(R.id.given_word);
        String new_word = given_word.getText().toString();
        if(new_word.matches("")){
            given_word.setHint("You did not fill in a word");
        }
        else {
            given_word.setText("");
            story.fillInPlaceholder(new_word);
            if (story.isFilledIn()){
                GoToThird(story);
            }
            createText(story);
        }
    }

    /* Starts the third activity when called upon. */
    private void GoToThird(Story story) {
        Intent intent = new Intent (this, ThirdActivity.class);
        intent.putExtra("story", story);
        startActivity(intent);
    }

    /* Creates the text layout for this activity. Shows how many words are left
    *  and what kind of word the user should type in. */
    public void createText(Story story){
        TextView words_left = (TextView) findViewById(R.id.words_left);
        TextView word_type = (TextView) findViewById(R.id.word_type);
        EditText hint_word = (EditText) findViewById(R.id.given_word);
        int placeholder_count = story.getPlaceholderRemainingCount();
        String text_words_left = placeholder_count + " word(s) left";
        words_left.setText(text_words_left);
        String text_word_type = "Please type a/an " + story.getNextPlaceholder();
        hint_word.setHint(story.getNextPlaceholder());
        word_type.setText(text_word_type);
    }

}
