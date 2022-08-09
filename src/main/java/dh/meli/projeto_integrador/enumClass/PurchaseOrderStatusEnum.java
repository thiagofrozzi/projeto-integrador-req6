package dh.meli.projeto_integrador.enumClass;

public enum PurchaseOrderStatusEnum {
    OPEN("Open"), FINISHED("Finished"); // enums são constantes

    String statusCart;

    PurchaseOrderStatusEnum(String status) {
        this.statusCart = status;
    }
}
