package com.example.schedulab;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
   /* @Mock
    MainActivity view;

    @Mock
    Model model;

    @Test
    public void testPresenterEmptyEmail(){
        when(view.getEmail()).thenReturn("");
        when(model.checkLogin("", 123456)).thenReturn(false);
        Presenter presenter = new Presenter(model, view);
        presenter.onLoginButton();
        verify(view).emailEmpty(setError);
    }
    @Test
    public void testPresenterEmptyPassword(){
        when(view.getPassword()).thenReturn("");
        // something for model
        Presenter presenter = new Presenter(model, view);
        presenter.onLoginButton();
        verify(view).emailEmpty(setError);
    }
    @Test
    public void testPresenterEmptyEmail(){
        when(view.getEmail()).thenReturn("");
        // something for model
        Presenter presenter = new Presenter(model, view);
        presenter.onLoginButton();
        verify(view).emailEmpty(setError);
    }*/



    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}