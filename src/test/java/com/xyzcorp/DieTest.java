package com.xyzcorp;

import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;

public class DieTest {

    @Test
    public void testDieWithARollOf4() {
        Random randomStub = new Random() {
            @Override
            public int nextInt(int bound) {
                return 3;
            }
        };

        Die die = new Die(randomStub);
        Die rolledDie = die.roll();
        assertThat(rolledDie.getPips()).isEqualTo(4);
    }

    @Test
    public void testDieDefaultOf1WithRandom() {
        Random randomStub = new Random() {
            @Override
            public int nextInt(int bound) {
                return 4;
            }
        };

        Die die = new Die(randomStub);
        assertThat(die.getPips()).isEqualTo(1);
    }


    @Test
    public void testDieRollUsingARandomMockUsing3() {
        Random randomMock = createMock(Random.class);

        //rehearse with the mock
        expect(randomMock.nextInt(6)).andReturn(2);

        //replay (easymock only)
        replay(randomMock);

        Die die = new Die(randomMock);
        assertThat(die.roll().getPips()).isEqualTo(3);

        //verify (easymock and mockito)
        verify(randomMock);
    }

    //JUnitGroups to run this separate from a unit test
    @Test
    public void testIntegrationTest() {
        Die die = new Die(new Random());
        for (int i = 0; i < 1000000; i++) {
            assertThat(die.roll().getPips()).isGreaterThan(0).isLessThan(7);
        }
    }

    @Test
    public void testBUG3012() {
        Random randomMock = createMock(Random.class);

        //rehearse with the mock
        expect(randomMock.nextInt(6)).andReturn(0);

        //replay (easymock only)
        replay(randomMock);

        Die die = new Die(randomMock);
        assertThat(die.roll().getPips()).isGreaterThan(0).isLessThan(7);

        //verify (easymock and mockito)
        verify(randomMock);
    }
}
