package client.lib

import spock.lang.Specification
import spock.lang.Unroll

import static client.lib.TestBombermanSolver.STEP.*


class BombermanSolverSpec extends Specification {

    @Unroll
    def 'Correct step for going #direction.name() and #action is #expectedMove'() {
        when: 'Solver choose direction'
        def testSolver = new TestBombermanSolver(direction)

        then: 'It performs correct action'
        testSolver.move() == expectedMove

        where:
        direction      | action        | expectedMove
        UP             | 'NONE'        | 'UP'
        DOWN           | 'NONE'        | 'DOWN'
        LEFT           | 'NONE'        | 'LEFT'
        RIGHT          | 'NONE'        | 'RIGHT'
        ACT            | 'NONE'        | 'ACT'
        ACT_AND_UP     | 'BEFORE_TURN' | 'ACT,UP'
        ACT_AND_DOWN   | 'BEFORE_TURN' | 'ACT,DOWN'
        ACT_AND_LEFT   | 'BEFORE_TURN' | 'ACT,LEFT'
        ACT_AND_RIGHT  | 'BEFORE_TURN' | 'ACT,RIGHT'
        UP_AND_ACT     | 'AFTER_TURN'  | 'UP,ACT'
        DOWN_AND_ACT   | 'AFTER_TURN'  | 'DOWN,ACT'
        LEFT_AND_ACT   | 'AFTER_TURN'  | 'LEFT,ACT'
        RIGHT_AND_ACT  | 'AFTER_TURN'  | 'RIGHT,ACT'
    }
}
