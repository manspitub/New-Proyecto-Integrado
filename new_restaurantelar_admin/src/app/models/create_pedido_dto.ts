export class CreatePedidoDto {
    
    fullNameClient: string;
    typePayment: number;
    idsPlato: number[];
    dateReserva: string;
    numMesa: number;
    type: number;
    idWaiter: number;
    
    constructor() {
      this.fullNameClient = '';
      this.typePayment = 0;
      this.idsPlato = [];
      this.dateReserva = '';
      this.numMesa = 0;
      this.type = 0;
      this.idWaiter = 0 ;
    }
  }