import { Question } from "./question.model";

export interface Test {
    id: number;
    dateCreation: Date;
    type: TestType;
    technologie: string;
    questions: Question[];
  }
  export enum TestType {
    IQ = 'IQ',
    TECHNIQUE = 'TECHNIQUE'
  }
