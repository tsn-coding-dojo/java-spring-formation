// Generated using typescript-generator version 2.1.406 on 2018-03-07 17:57:54.

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
