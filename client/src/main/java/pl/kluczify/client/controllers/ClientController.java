package pl.kluczify.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller

public class ClientController {
    @Autowired
    ClientServerController clientServerController;

    @Autowired
    ClientLockController clientLockController;

    public ClientController() {
    }

    public ClientServerController getClientServerController() {
        return clientServerController;
    }

    public ClientLockController getClientLockController() {
        return clientLockController;
    }

    public void setClientServerController(ClientServerController clientServerController) {
        this.clientServerController = clientServerController;
    }

    public void setClientLockController(ClientLockController clientLockController) {
        this.clientLockController = clientLockController;
    }
}
