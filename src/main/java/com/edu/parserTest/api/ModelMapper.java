package com.edu.parserTest.api;

import com.edu.parserTest.business.atmService.CurrencyNotesModel;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public CurrencyNotesModel fromVmToModel(CurrencyNotesVM vm) {
        return new CurrencyNotesModel(vm.getNotesOf5(), vm.getNotesOf10(), vm.getNotesOf20(), vm.getNotesOf50());
    }

    public CurrencyNotesVM fromModelToVs(CurrencyNotesModel model) {
        return new CurrencyNotesVM(model.getNotesOf5(),model.getNotesOf10(),model.getNotesOf20(), model.getNotesOf50());
    }
}
