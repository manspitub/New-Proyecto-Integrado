export interface PlatoResponse {
    content:          Plato[];
    pageable:         Pageable;
    totalPages:       number;
    totalElements:    number;
    last:             boolean;
    size:             number;
    number:           number;
    sort:             Sort;
    first:            boolean;
    numberOfElements: number;
    empty:            boolean;
  }
  
  export interface Plato {
    id:              number;
    name:            string;
    description:     string;
    distinct:        boolean;
    imageUrl:        string;
    price:           number;
    nameCookMan:     string
  }
  
  export interface Pageable {
    sort:       Sort;
    offset:     number;
    pageNumber: number;
    pageSize:   number;
    paged:      boolean;
    unpaged:    boolean;
  }
  
  export interface Sort {
    empty:    boolean;
    sorted:   boolean;
    unsorted: boolean;
  }