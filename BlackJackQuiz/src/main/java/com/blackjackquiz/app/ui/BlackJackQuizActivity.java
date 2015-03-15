package com.blackjackquiz.app.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.blackjackquiz.app.R;
import com.blackjackquiz.app.deck.CardImageLoader;
import com.blackjackquiz.app.solution.SolutionManual;

public class BlackJackQuizActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_quiz);

        m_blackJackQuizFragment = new BlackJackQuizFragment();
        m_solutionTableFragment = new SolutionTableFragment();

        getFragmentManager().beginTransaction()
                .add(R.id.container, m_blackJackQuizFragment)
                .add(R.id.container, m_solutionTableFragment)
                .hide(m_solutionTableFragment)
                .show(m_blackJackQuizFragment)
                .commit();

        // this is so the initialization code is kicked off
        SolutionManual.getInstance(this);
        CardImageLoader.getInstance(this);
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
        switch (id)
        {
        case R.id.black_jack_quiz:
            getFragmentManager().beginTransaction()
                    .hide(m_solutionTableFragment)
                    .show(m_blackJackQuizFragment)
                    .commit();
            return true;
        case R.id.solution_table:
            getFragmentManager().beginTransaction()
                    .hide(m_blackJackQuizFragment)
                    .show(m_solutionTableFragment)
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BlackJackQuizFragment m_blackJackQuizFragment;
    private SolutionTableFragment m_solutionTableFragment;
}
