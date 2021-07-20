package com.ashunevich.android_retrofit2_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

@RunWith (MockitoJUnitRunner.class)
public class ApiMockTest{

    @Mock
    Contractor.View view;

    private PresenterImpl presenter;

    @Captor
    ArgumentCaptor<List<ItemJSON>> jsons;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks (this);
        presenter = new PresenterImpl (view,new InteractorImpl ());
    }

    @Test
    public void loadPost() {
        presenter.getPosts ();
        verify(view).parseDataToRecyclerView (jsons.capture ());
        Assert.assertEquals (2, jsons.capture ().size ());
    }
}
