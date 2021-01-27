package de.SetMyRoute;

/*
 *  Created on 23.01.2021 @ 17:03 by Adrian Petzold -
 */

import de.SetMyRoute.controller.DifficultyRepository;
import de.SetMyRoute.model.difficulty.Difficulty;
import de.SetMyRoute.model.difficulty.Signedness;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiffcultyTest
{
    @Autowired
    public DifficultyRepository difficultyRepository;


    @Test
    void testInsertDifficulty()
    {
        final Difficulty difficultyNegative = new Difficulty((byte) 5, Signedness.NEGATIVE);
        final Difficulty difficultyNone = new Difficulty((byte) 6, Signedness.NONE);
        final Difficulty difficultyPositive = new Difficulty((byte) 7, Signedness.POSITIVE);
        difficultyRepository.save(difficultyNegative);
        difficultyRepository.save(difficultyNone);
        difficultyRepository.save(difficultyPositive);
    }
}
