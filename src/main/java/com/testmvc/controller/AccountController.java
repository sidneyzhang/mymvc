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
import org.thymeleaf.util.ListUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AccountController
 */


@Controller
@RequestMapping("/pages")
public class AccountController {

    private final Logger log = Logger.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @ModelAttribute("accountinfo")
    public List<Account> prepareAccountInfo() {
        return this.accountService.getAccountList();
    }

    @RequestMapping("/accountinfo")
    public String showAccount(Model model,final Account account) {
        log.info("查询账户信息");
        List<Account> accountinfo= this.accountService.getAccountList();
        log.info("账户条数:"+ ListUtils.size(accountinfo));
        this.prepareAccountInfo();
        return "accountinfo";

    }

    @RequestMapping(value = "/accountinfo",params = {"save"})
    public String saveAccount(@ModelAttribute(value = "account") Account account, final BindingResult bindingResult, final Model model){
        log.info("新增账户信息");
        accountService.insertAccount(account);
        model.asMap().clear();
        return "redirect:/accountinfo";
    }

}
