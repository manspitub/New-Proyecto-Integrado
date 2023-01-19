export interface AuthLoginResponse{
    email: string,
    avatar: string,
    username: string,
    fullName: string,
    role: string,
    offWork: boolean,
    idWorker: number,
    salaryAmount: number,
    token: string;
}