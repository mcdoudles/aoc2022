package days

/**
 * @see <a href="https://adventofcode.com/2022/day/7">Advent of Code Day 7</a>
 */
class Day07(lines: List<String>) : Day(lines) {
    class Directory(var name: String, var containedIn: Directory?) {
        var subDirectories: Set<Directory> = setOf()
        var size: Int = 0

        override fun toString(): String {
            return "$name: $size"
        }
    }

    override fun task1(): String {
        val root = createFileSystem()
        var result = addDirectorySize(root, 1)
        // Just in case root is small enough as well (it can't be because of task 2)
        if (root.size <= 100000) {
            result += root.size
        }

        return result.toString()
    }

    override fun task2(): String {
        val root = createFileSystem()
        val requiredSpace = root.size - 40000000
        val result = findDirToDelete(root, requiredSpace, root.size)
        return result.toString()
    }

    // Recursion is nice, we all love it
    private fun findDirToDelete(dir: Directory, requiredSpace: Int, currentBest: Int): Int {
        var best = currentBest
        if (dir.size in (requiredSpace + 1) until best) {
            best = dir.size
        }
        for (subDir in dir.subDirectories) {
            best = findDirToDelete(subDir, requiredSpace, best)
        }
        return best
    }

    // Recursion is nice, we all love it
    private fun addDirectorySize(dir: Directory, depth: Int): Int {
        var result = 0
        for (subDir in dir.subDirectories) {
            if (subDir.size <= 100000) {
                result += subDir.size
            }
            if (subDir.subDirectories.isNotEmpty()) {
                result += addDirectorySize(subDir, depth + 1)
            }
        }
        return result
    }

    private fun createFileSystem(): Directory {
        // Eliminate useless lines / chars and split between commands and assigns
        val filteredLines =
            lines.filter { it != "$ ls" && it != "$ cd /" }.map { it.replace("$", "").trim().split(" ") }
        val root = Directory("root", null)
        var currentDirectory = root
        for (line in filteredLines) {
            when (line[0]) {
                "dir" -> {
                    val directory = Directory(line[1], currentDirectory)
                    currentDirectory.subDirectories = currentDirectory.subDirectories + directory
                }

                "cd" -> {
                    if (line[1] == "..") {
                        currentDirectory = currentDirectory.containedIn!!
                        continue
                    }
                    currentDirectory = currentDirectory.subDirectories.find { directory -> directory.name == line[1] }!!
                }
                // Has to be a file, there are no other commands.
                else -> {
                    val fileSize = Integer.parseInt(line[0])
                    var increaseDirectorySize = currentDirectory
                    while (true) {
                        increaseDirectorySize.size += fileSize
                        if (increaseDirectorySize.containedIn != null) {
                            increaseDirectorySize = increaseDirectorySize.containedIn!!
                        } else {
                            break
                        }
                    }
                }
            }
        }
        return root
    }
}