export interface UsersResponse {
    content: UserDto[];
    pageable: Pageable;
    last: boolean;
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
    sort: Sort2;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
}

  export interface UserDto {
      id: string;
      avatar: string;
      username: string;
      fullName: string;
      email: string;
      role: string;
      points: number,
      verified: boolean,
      address: string

  }

  export interface Sort {
      empty: boolean;
      sorted: boolean;
      unsorted: boolean;
  }

  export interface Pageable {
      sort: Sort;
      offset: number;
      pageNumber: number;
      pageSize: number;
      unpaged: boolean;
      paged: boolean;
  }

  export interface Sort2 {
      empty: boolean;
      sorted: boolean;
      unsorted: boolean;
  }