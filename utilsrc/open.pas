program pre;
uses System.IO;

function FileExist(name : string) : boolean;
var f: text;
begin
  assign(f, name);
  try
    reset(f);
    close(f);
    result := true;
  except
    result := false;
  end;
end;

var
  n, filecounter: integer;
  files: array of string;
  filename: string;

begin
  files := Directory.GetFiles('.', '*.greenfoot', SearchOption.TopDirectoryOnly);
  
  filecounter := 1;
  for var i := 0 to length(files)-1 do begin
    filename := files[i].Split('\')[1];
    if(filename <> 'project.greenfoot') then begin
      writeln(filecounter, ': ', filename);
      inc(filecounter);
    end;
  end;
  
  if FileExist('project.greenfoot') then begin
    &File.Delete('project.greenfoot');
  end;
  
  writeln;
  write('Enter project number: ');
  readln(n);
  
  if not FileExist('project' + n + '.greenfoot') then begin
    writeln('project', n, '.greenfoot');
    exit;
  end;
  
  &File.Copy('project' + n + '.greenfoot', 'project.greenfoot');
  writeln('Opening');
  Execute('project.greenfoot');
end.