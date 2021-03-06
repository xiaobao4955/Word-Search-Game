package com.aar.app.wordsearch.data.mapper;


import com.aar.app.wordsearch.commons.Mapper;
import com.aar.app.wordsearch.commons.generator.StringGridGenerator;
import com.aar.app.wordsearch.data.entity.GameRoundEntity;
import com.aar.app.wordsearch.model.GameRound;
import com.aar.app.wordsearch.model.Grid;

/**
 * Created by abdularis on 08/07/17.
 */

public class GameRoundMapper extends Mapper<GameRoundEntity, GameRound> {
    @Override
    public GameRound map(GameRoundEntity obj) {
        if (obj == null) return null;

        GameRound gr = new GameRound();
        gr.setInfo(obj.getInfo());
        Grid grid = new Grid(obj.getGridRowCount(), obj.getGridColCount());
        gr.setGrid(grid);

        if (obj.getGridData() != null && obj.getGridData().length() > 0) {
            new StringGridGenerator().setGrid(obj.getGridData(), grid.getArray());
        }

        gr.addUsedWords(obj.getUsedWords());

        return gr;
    }

    @Override
    public GameRoundEntity revMap(GameRound obj) {
        if (obj == null) return null;

        GameRoundEntity ent = new GameRoundEntity();
        ent.setInfo(obj.getInfo());

        if (obj.getGrid() != null) {
            ent.setGridRowCount(obj.getGrid().getRowCount());
            ent.setGridColCount(obj.getGrid().getColCount());
            ent.setGridData(obj.getGrid().toString());
        }

        ent.setUsedWords(obj.getUsedWords());

        return ent;
    }
}
