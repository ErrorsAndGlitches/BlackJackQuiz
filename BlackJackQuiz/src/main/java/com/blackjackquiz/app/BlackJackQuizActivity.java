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
import android.widget.ImageView;
import android.widget.TextView;
import com.blackjackquiz.app.deck.CardImageLoader;
import com.blackjackquiz.app.deck.Deck.Card;
import com.blackjackquiz.app.deck.Field;
import com.blackjackquiz.app.solution.SolutionManual;

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

        m_solutionManual = new SolutionManual(this);
        CardImageLoader.getInstance(this); // this is so the image loading is kicked off
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
        private static final String DEALER_CARD_PREFIX     = "Dealer  : ";
        private static final String PLAYER_ONE_CARD_PREFIX = "Card One: ";
        private static final String PLAYER_TWO_CARD_PREFIX = "Card Two: ";
        public static final  String SOLUTION_HINT_TEXT     = "Solution";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_black_jack_quiz, container, false);

            m_dealerCardText = (TextView) rootView.findViewById(R.id.dealer_card);
            m_playerCardOneText = (TextView) rootView.findViewById(R.id.player_card_one);
            m_playerCardTwoText = (TextView) rootView.findViewById(R.id.player_card_two);

            m_dealerCardImage = (ImageView) rootView.findViewById(R.id.dealer_card_img);
            m_playerCardOneImage = (ImageView) rootView.findViewById(R.id.player_card_one_img);
            m_playerCardTwoImage = (ImageView) rootView.findViewById(R.id.player_card_two_img);

            m_solutionText = (TextView) rootView.findViewById(R.id.solution_text);

            Button nextCardButton = (Button) rootView.findViewById(R.id.next_field_button);
            setupNextFieldButton(nextCardButton);

            Button showSolutionButton = (Button) rootView.findViewById(R.id.show_solution_button);
            setupShowSolutionButton(showSolutionButton);

            return rootView;
        }

        @Override
        public void onStart()
        {
            super.onStart();
            newField();
        }

        private void setupShowSolutionButton(Button showSolutionButton)
        {
            showSolutionButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    SolutionManual solMan = ((BlackJackQuizActivity) getActivity()).m_solutionManual;
                    SolutionManual.BlackJackAction action = solMan.getSolutionForCards(m_field.dealerCard,
                                                                                       m_field.playerCardOne,
                                                                                       m_field.playerCardTwo);
                    m_solutionText.setText(action.name());
                }
            });
        }

        private void setupNextFieldButton(Button nextCardButton)
        {
            nextCardButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    newField();
                }
            });
        }

        private void newField()
        {
            m_field = Field.newField();
            resetText();
            resetImages();
        }

        private void resetText()
        {
            setTextForCard(DEALER_CARD_PREFIX, m_dealerCardText, m_field.dealerCard);
            setTextForCard(PLAYER_ONE_CARD_PREFIX, m_playerCardOneText, m_field.playerCardOne);
            setTextForCard(PLAYER_TWO_CARD_PREFIX, m_playerCardTwoText, m_field.playerCardTwo);
            resetSolutionText();
        }

        private void resetImages()
        {
            CardImageLoader cardImageLoader = CardImageLoader.getInstance(getActivity());
            m_dealerCardImage.setImageBitmap(cardImageLoader.getBitmapForCard(m_field.dealerCard));
            m_playerCardOneImage.setImageBitmap(cardImageLoader.getBitmapForCard(m_field.playerCardOne));
            m_playerCardTwoImage.setImageBitmap(cardImageLoader.getBitmapForCard(m_field.playerCardTwo));
        }

        private static void setTextForCard(String prefix, TextView textView, Card card)
        {
            textView.setText(String.format("%s(%s, %s)", prefix, card.suite, card.rank));
        }

        private void resetSolutionText()
        {
            m_solutionText.setText(null);
            m_solutionText.setHint(SOLUTION_HINT_TEXT);
        }

        private TextView m_dealerCardText;
        private TextView m_playerCardOneText;
        private TextView m_playerCardTwoText;

        private ImageView m_dealerCardImage;
        private ImageView m_playerCardOneImage;
        private ImageView m_playerCardTwoImage;

        private TextView m_solutionText;
        private Field    m_field;
    }

    private SolutionManual  m_solutionManual;
}
