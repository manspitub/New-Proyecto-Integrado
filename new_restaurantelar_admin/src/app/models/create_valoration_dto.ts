export class CreatePropertyDto {
    criticEmail: string;
    platoId: number;
    rating: number;
    comments: string;
    
    constructor() {
      this.criticEmail = '';
      this.platoId = 0;
      this.rating = 0;
      this.comments = '';
      }
  }