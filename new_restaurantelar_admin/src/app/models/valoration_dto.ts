
export interface ValorationResponse {
    content: Valoration[];
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
  
  export interface Valoration {
    id: number;
    nombrePlato: string;
    nombreUser: string;
    comments: string;
    rating: number;
    validated: boolean;
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