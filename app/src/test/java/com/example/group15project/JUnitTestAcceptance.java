package com.example.group15project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.Silent.class)

public class JUnitTestAcceptance {
    static AcceptanceActivity accActivity = null;

    //AT-I
    @Test
    public void acceptanceWorks()
    {
        AcceptanceActivity accActivity = Mockito.mock(AcceptanceActivity.class);
        Mockito.when(accActivity.getAcceptance()).thenReturn(1);
        accActivity.createList();
        assertEquals(1, accActivity.getAcceptance());
        Mockito.verify(accActivity, Mockito.atLeastOnce()).getAcceptance();
    }
    @Test
    public void declineWorks()
    {
        AcceptanceActivity accActivity = Mockito.mock(AcceptanceActivity.class);
        Mockito.when(accActivity.getDeclines()).thenReturn(1);
        accActivity.createList();
        assertEquals(1, accActivity.getDeclines());
        Mockito.verify(accActivity, Mockito.atLeastOnce()).getDeclines();
    }


}
