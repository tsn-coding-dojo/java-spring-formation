export class SessionDto {
    login: string;
}

export class TodoDto {
    id: number;
    version: number;
    name: string;
    user: string;
}

export type ErrorCode = "FORBIDDEN" | "UNAUTHORIZED" | "BAD_REQUEST" | "NOT_FOUND" | "CONFLICT" | "INTERNAL_SERVER_ERROR" | "PRECONDITION_FAILED" | "SERVICE_UNAVAILABLE";

export type Role = "USER" | "ADMIN";

export type TodoStatus = "TODO" | "COMPLETED";
