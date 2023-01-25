export class CreatePlatoDto {
    name: string;
    description: string;
    distinct: boolean;
    imageUrl: string;
    price: number;
    
    constructor() {
      this.name = '';
      this.description = '';
      this.distinct = false;
      this.imageUrl = '';
      this.price = 0;
    }
  }