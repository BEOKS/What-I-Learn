INSERT INTO score (student_name, subject_name, point, student_id, subject_id, score_id) VALUES
                                                                                            ('김철수', '수학', 85, 1, 101, 1001),
                                                                                            ('이영희', '수학', 90, 2, 101, 1002),
                                                                                            ('박지민', '과학', 75, 3, 102, 1003),
                                                                                            ('최유나', '영어', 80, 4, 103, 1004),
                                                                                            ('김민수', '역사', 70, 5, 104, 1005);

INSERT INTO student (student_name, subject_name, grade, student_id, subject_id, student_subject_id) VALUES
                                                                                                        ('김철수', '수학', 'A', 1, 101, 10001),
                                                                                                        ('이영희', '수학', 'A+', 2, 101, 10002),
                                                                                                        ('박지민', '과학', 'B', 3, 102, 10003),
                                                                                                        ('최유나', '영어', 'B+', 4, 103, 10004),
                                                                                                        ('김민수', '역사', 'C', 5, 104, 10005);

INSERT INTO subject (student_name, subject_name, description, student_id, subject_id, unique_subject_key) VALUES ('김철수', '수학', '고등학교 수학', 1, 101, 1001);
INSERT INTO subject (student_name, subject_name, description, student_id, subject_id, unique_subject_key) VALUES ('이영희', '영어', '고급 영어 문법', 2, 102, 1002);
INSERT INTO subject (student_name, subject_name, description, student_id, subject_id, unique_subject_key) VALUES ('박지민', '과학', '일반 과학 원리', 3, 103, 1003);
INSERT INTO subject (student_name, subject_name, description, student_id, subject_id, unique_subject_key) VALUES ('최유리', '역사', '한국사 개론', 4, 104, 1004);
INSERT INTO subject (student_name, subject_name, description, student_id, subject_id, unique_subject_key) VALUES ('정태영', '컴퓨터', '컴퓨터 기초 및 프로그래밍', 5, 105, 1005);
