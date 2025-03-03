package dungeonFiles;

import semanticanalysis.types.DSLType;
import semanticanalysis.types.DSLTypeMember;

import taskdependencygraph.TaskDependencyGraph;

/**
 * Stores information about a Task-based Game.
 *
 * @param dependencyGraph The task dependency graph that contains all the tasks of the game and
 *     their relationship.
 * @param displayName Name of the Game.
 */
@DSLType
public record DungeonConfig(
        @DSLTypeMember TaskDependencyGraph dependencyGraph,
        @DSLTypeMember(name = "name") String displayName) {}
