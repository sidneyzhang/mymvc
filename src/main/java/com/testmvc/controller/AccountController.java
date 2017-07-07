package com.testmvc.controller;

import com.testmvc.domain.Account;
import com.testmvc.service.AccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * AccountController
 */


@Controller
public class AccountController {

    private final Logger log = Logger.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @ModelAttribute(value = "accountinfo")
    public List<Account> prepareAccountInfo() {
        return this.accountService.getAccountList();
    }

    @RequestMapping(value = {"/", "/accountinfo"})
    public String showAccount(Model model) {
        log.info("查询账户信息");
        model.addAttribute("account", new Account());
        return "accountinfo";
    }

    @RequestMapping(value = "/accountinfo", params = {"save"})
    public String saveAccount(Account account, final BindingResult bindingResult, final Model model) {
        log.info("新增账户信息");
        accountService.insertAccount(account);
        model.asMap().remove("account");
        // model.addAttribute("accountinfo",new Account());
        return "accountinfo";
    }

}
