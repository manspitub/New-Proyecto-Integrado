
export interface PedidoResponse {
    content: Pedido[];
    pageable: Pageable;
    last: boolean;
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
    sort: Sort;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
  }
  
  export interface Pedido {
    id: number;
    fullNameClient: string;
    date: string;
    type: number;
    dishRated: boolean;
    completed: boolean;
    cancelled: boolean;
    usernameWaiter: string;
    namePlato: string;
    paymentAmount: number;
    numTable: number;
  }
  
  export interface Pageable {
    sort: Sort;
    offset: number;
    pageNumber: number;
    pageSize: number;
    paged: boolean;
    unpaged: boolean;
  }
  
  export interface Sort {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
  }