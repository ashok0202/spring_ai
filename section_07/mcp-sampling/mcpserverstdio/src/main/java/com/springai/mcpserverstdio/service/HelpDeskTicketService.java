package com.springai.mcpserverstdio.service;

import com.springai.mcpserverstdio.entity.HelpDeskTicket;
import com.springai.mcpserverstdio.repository.HelpDeskTicketRepository;
import com.springai.mcpserverstdio.model.TicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HelpDeskTicketService {

    private final HelpDeskTicketRepository helpDeskTicketRepository;
    public HelpDeskTicketService(HelpDeskTicketRepository helpDeskTicketRepository) {
        this.helpDeskTicketRepository = helpDeskTicketRepository;
    }

    public HelpDeskTicket createTicket(TicketRequest ticketInput) {
        HelpDeskTicket ticket = new HelpDeskTicket();
        ticket.setUsername(ticketInput.username());
        ticket.setIssue(ticketInput.issue());
        ticket.setStatus("OPEN");
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setEta(LocalDateTime.now().plusDays(7));
        return helpDeskTicketRepository.save(ticket);
    }

    public List<HelpDeskTicket> getTicketsByUsername(String username) {
        return helpDeskTicketRepository.findByUsername(username);
    }
}