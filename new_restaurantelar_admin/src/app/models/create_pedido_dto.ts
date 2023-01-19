export class CreatePedidoDto {
    
    fullNameClient: string;
    type: number;
    idsPlato: number[];
    dateReserva: string;
    numMesa: number;
    
    constructor() {
      this.fullNameClient = '';
      this.type = 0;
      this.idsPlato = [];
      this.dateReserva = '';
      this.numMesa = 0;
    }
  }