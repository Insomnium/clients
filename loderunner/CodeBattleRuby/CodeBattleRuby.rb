require_relative 'CodeBattleRubyLibrary'

def isBlock?(block)
  [
    :Gold,
    :Space,
    :Ladder,
    :Pipe,
    :PitFill1,
    :PitFill2,
    :PitFill3,
    :PitFill4,
    :DrillPit
  ].include?(block) == false
end

def turn(gc)
  done = false
  r = rand(5)
  case r
  when 0
    if isBlock?(gc.map[gc.playerY - 1][gc.playerX]) == false
      gc.up()
      done = true
    end
  when 1
    if isBlock?(gc.map[gc.playerY - 1][gc.playerX]) == false
      gc.down()
      done = true
    end
  when 2
    if isBlock?(gc.map[gc.playerY - 1][gc.playerX]) == false
      gc.right()
      done = true
    end
  when 3
    if isBlock?(gc.map[gc.playerY - 1][gc.playerX]) == false
      gc.left()
      done = true
    end
  when 4
    gc.act
    done = true
  else
  end
  if done == false
    gc.blankg
  end
end

w = GameClient.new("192.168.0.25:8080", "a@b.ru", "123")
w.run(method(:turn))
