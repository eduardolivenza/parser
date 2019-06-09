package com.edu.parserTest.api;

import com.edu.parserTest.business.ATMService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ComponentScan("com.edu.parserTest")
@EnableAutoConfiguration
class ATMServiceAPI {

    private ATMService atm;
    private ModelMapper mapper;

    @Autowired
    public ATMServiceAPI(ATMService atm, ModelMapper mapper) {
        this.atm = atm;
        this.mapper = mapper;
    }

    @RequestMapping("/")
    @ResponseBody
    String root() {
        return "Hello, I'm your ATM";
    }

    @PostMapping("/replenish")
    public void replenish(@RequestBody CurrencyNotesVM newNotes)
    {
        this.atm.replenish(mapper.fromVmToModel(newNotes));
    }

    @RequestMapping("/checkBalance/{accountId}")
    @ResponseBody
    public String checkBalance(@PathVariable String accountId)
    {
        return atm.checkBalance(accountId);
    }

    @RequestMapping("/withdrawal/{accountId}/{quantity}")
    @ResponseBody
    public CurrencyNotesVM withdrawal(@PathVariable String accountId, @PathVariable Integer quantity){
        CurrencyNotesVM currencyNotesVM = new CurrencyNotesVM(0,0,0,0);
        try {
            currencyNotesVM = mapper.fromModelToVs(this.atm.withdrawal(accountId, quantity));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  currencyNotesVM;
    }

}
