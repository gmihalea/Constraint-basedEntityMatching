# EntityMatching
Constraint-based entity matching algorithm

Entity matching is the problem of deciding if two given objects or data instances,
refer to the same entity. This task has an important role in many domains such
information integration, natural language understanding, information processing
on the World Wide Web or emerging Semantic Web.

Input:

Entities A       Entities B          Constraints
entityA1         entityB1            constraint1
entityA2         entityB2            constraint2
...              ...                 ...
entityAn         entityBm            constraintp


Output:
(entityAi - entityBj, entityBk, ..., entityBs),
(entityAe - entityBy, entityBh, ..., entityBs),
...
(entityAm - entityBl, entityBq, ..., entityBd)
