# Scala Build Tool
SBT = sbt

# PATHS
PATH_UNARY_ADD = res/unary_add.json
PATH_PALINDROME = res/palindrome_decider.json
PATH_0N1N = res/0n1n.json
PATH_02N = res/02n.json
PATH_UNARY_ADD_RUNNER = res/unary_add_runner.json

all: compile

compile:
	@$(SBT) compile

run1:
	@$(SBT) "run $(PATH_UNARY_ADD) $(INPUT)"

run2:
	@$(SBT) "run $(PATH_PALINDROME) $(INPUT)"

run3:
	@$(SBT) "run $(PATH_0N1N) $(INPUT)"

run4:
	@$(SBT) "run $(PATH_02N) $(INPUT)"

run5:
	@$(SBT) "run $(PATH_UNARY_ADD_RUNNER) $(INPUT)"

clean:
	@$(SBT) clean