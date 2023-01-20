export class AuthSignUpDto {
    username: string;
    fullName: string;
    email: string;
    avatar: string;
    offWork: boolean;
    code: string;
    code2: string;
    salaryAmount: number;
    constructor() {
        this.email = '';
        this.fullName = '';
        this.username = '';
        this.code = '';
        this.avatar = '';
        this.offWork = false,
        this.code2 = '',
        this.salaryAmount = 0
    }
}