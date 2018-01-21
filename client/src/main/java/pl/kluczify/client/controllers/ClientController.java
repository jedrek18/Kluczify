package pl.kluczify.client.controllers;

public class ClientController {
    ClientServerController clientServerController;
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
