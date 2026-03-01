package com.spiringai.open_ai.service;


import com.spiringai.open_ai.entity.HelpDeskTicket;
import com.spiringai.open_ai.model.TicketRequest;
import com.spiringai.open_ai.repository.HelpDeskTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HelpDeskTicketService {

    @Autowired
    private final HelpDeskTicketRepository helpDeskTicketRepository;

    public HelpDeskTicketService(HelpDeskTicketRepository helpDeskTicketRepository) {
        this.helpDeskTicketRepository = helpDeskTicketRepository;
    }

    public HelpDeskTicket createTicket(TicketRequest ticketRequest, String username) {
        HelpDeskTicket helpDeskTicket = new HelpDeskTicket();
        helpDeskTicket.setIssue(ticketRequest.issue());
        helpDeskTicket.setUsername(username);
        helpDeskTicket.setStatus("OPEN");
        helpDeskTicket.setCreatedAt(LocalDateTime.now());
        helpDeskTicket.setEta(LocalDateTime.now().plusDays(7));
        return helpDeskTicketRepository.save(helpDeskTicket);
    }

    public List<HelpDeskTicket> getTicketsByUsername(String username) {
        return helpDeskTicketRepository.findByUsername(username);
    }
}
