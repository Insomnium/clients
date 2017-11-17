require 'websocket'
require 'websocket-eventmachine-client'

Blocks =
{
  Space: ' ',

  Brick: '#',
  PitFill1: '1',
  PitFill2: '2',
  PitFill3: '3',
  PitFill4: '4',
  UndestroyableWall: '☼',

  DrillPit: '*',

  EnemyLadder: 'Q',
  EnemyLeft: '«',
  EnemyRight: '»',
  EnemyPipeLeft: '<',
  EnemyPipeRight: '>',
  EnemyPit: 'X',

  Gold: '$',

  HeroDie: 'Ѡ',
  HeroDrillLeft: 'Я',
  HeroDrillRight: 'R',
  HeroLadder: 'Y',
  HeroLeft: '◄',
  HeroRight: '►',
  HeroFallLeft: ']',
  HeroFallRight: '[',
  HeroPipeLeft: '{',
  HeroPipeRight: '}',

  OtherHeroDie: 'Z',
  OtherHeroLeft: ')',
  OtherHeroRight: '(',
  OtherHeroLadder: 'U',
  OtherHeroPipeLeft: 'Э',
  OtherHeroPipeRight: 'Є',

  Ladder: 'H',
  Pipe: '~'
}

module ActionTime
  None = 0,
  BeforeTurn = 1,
  AfterTurn = 2
end

class GameClient
  attr_reader :mapSize
  attr_reader :map
  attr_reader :playerX
  attr_reader :playerY

  def initialize(server, userEmail, userPassword = nil)
    @path = "ws://" + server + "/codenjoy-contest/ws?user=" + userEmail + (userPassword != nil ? ("&pwd=" + userPassword) : "")
  end

  def run(on_turn)
    EM.run do
      @ws = WebSocket::EventMachine::Client.connect(:uri => @path)
      
      @ws.onopen do
        puts "Connected"
      end
    
      @ws.onmessage do |message, type|
        message.force_encoding("UTF-8")
        message = message[6, message.length - 6]

        @mapSize = Math.sqrt(message.length).to_i
        @map = Array.new(@mapSize) {Array.new(@mapSize, :Unknown)}
        messageCur = 0
        for j in 0..(@mapSize - 1)
          for i in 0..(@mapSize - 1)
            Blocks.each do |key, value|
              if message[messageCur] == value
                @map[j][i] = key
                if [
                  :HeroDie,
                  :HeroDrillLeft,
                  :HeroDrillRight,
                  :HeroLadder,
                  :HeroLeft,
                  :HeroRight,
                  :HeroFallLeft,
                  :HeroFallRight,
                  :HeroPipeLeft,
                  :HeroPipeRight
                ].include?(key)
                  @playerX = i
                  @playerY = j
                end
                break
              end
            end
            messageCur = messageCur + 1
          end
        end
        
        on_turn.call(self)
      end
    
      @ws.onclose do |code, reason|
        puts "Disconnected with status code: #{code}"
      end
    end
  end
  
  def up(action = ActionTime::None)
    @ws.send "#{action == ActionTime::BeforeTurn ? "ACT," : ""}UP#{action == ActionTime::AfterTurn ? ",ACT" : ""}"
  end
  
  def down(action = ActionTime::None)
    @ws.send "#{action == ActionTime::BeforeTurn ? "ACT," : ""}DOWN#{action == ActionTime::AfterTurn ? ",ACT" : ""}"
  end
  
  def right(action = ActionTime::None)
    @ws.send "#{action == ActionTime::BeforeTurn ? "ACT," : ""}RIGHT#{action == ActionTime::AfterTurn ? ",ACT" : ""}"
  end
  
  def left(action = ActionTime::None)
    @ws.send "#{action == ActionTime::BeforeTurn ? "ACT," : ""}LEFT#{action == ActionTime::AfterTurn ? ",ACT" : ""}"
  end
  
  def act
    @ws.send "ACT"
  end
  
  def blank
    @ws.send ""
  end
end
