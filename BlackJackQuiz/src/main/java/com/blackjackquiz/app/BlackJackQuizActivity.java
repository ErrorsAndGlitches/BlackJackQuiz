package com.blackjackquiz.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.blackjackquiz.app.deck.Deck;

public class BlackJackQuizActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_quiz);
        if (savedInstanceState == null)
        {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new BlackJackQuizFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_black_jack_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class BlackJackQuizFragment extends Fragment
    {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_black_jack_quiz, container, false);

            m_randomCardTextView = (TextView) rootView.findViewById(R.id.random_card_text);
            Button nextCardButton = (Button) rootView.findViewById(R.id.next_random_card_button);
            nextCardButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    newRandomCard();
                }
            });

            return rootView;
        }

        private void newRandomCard()
        {
            final Deck.Card card = Deck.getRandomCard();
            getActivity().runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    m_randomCardTextView.setText(String.format("(%s, %s)", card.suite, card.value));
                }
            });
        }

        private TextView m_randomCardTextView;
    }
}
